package com.raphaelvilela.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.raphaelvilela.chatbot.dto.ChatMessageDTO;
import com.raphaelvilela.chatbot.ia.factory.AiAssistantFactory;
import com.raphaelvilela.chatbot.ia.factory.ContentRetrieverFactory;
import com.raphaelvilela.chatbot.ia.rag.EmbeddingFactory;
import com.raphaelvilela.chatbot.ia.rag.IaServiceFactory;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;

@Service
public class ChatService {

    @Value("${langchain.huggingface.accessToken}")
    private String huggingFaceToken;

    public String chatFromHuggingFace(ChatMessageDTO ChatMessageDTO) {
        ChatLanguageModel chat = AiAssistantFactory.createHuggingFaceChatModel(huggingFaceToken);
        return chat.generate(ChatMessageDTO.message()).trim();
    }   
    
    public String chatFromRAG(ChatMessageDTO ChatMessageDTO){

        ChatLanguageModel chatModel = AiAssistantFactory.createLocalChatModel();
        EmbeddingModel embeddingModel = EmbeddingFactory.createEmbeddingModel();
        EmbeddingStore<TextSegment> embeddingStore = EmbeddingFactory.createEmbeddingStore();
        
        var fileContentRetriever = ContentRetrieverFactory.createFileContentRetriever(
                embeddingModel,
                embeddingStore,
                "database.txt");
      
        var iaService = IaServiceFactory.createIaService(chatModel, fileContentRetriever);
        return iaService.chat(ChatMessageDTO.message()).trim();
    }
}
