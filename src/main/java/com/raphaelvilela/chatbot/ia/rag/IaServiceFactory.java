package com.raphaelvilela.chatbot.ia.rag;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;

public class IaServiceFactory {

    public static IaService createIaService(ChatLanguageModel chatModel, ContentRetriever contentRetriever){
        return AiServices.builder(IaService.class)
            .chatLanguageModel(chatModel)
            .contentRetriever(contentRetriever)
            .chatMemory(
                MessageWindowChatMemory.withMaxMessages(10)
            )
            .build();
    }
}
