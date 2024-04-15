package com.raphaelvilela.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raphaelvilela.chatbot.dto.ChatMessageDTO;
import com.raphaelvilela.chatbot.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatBotController {

    @Autowired
    ChatService chatService;

    @PostMapping("huggingFace")
    public ResponseEntity<String> chatFromHuggingFace(@RequestBody ChatMessageDTO messageDTO) {
        return ResponseEntity.ok().body(chatService.chatFromHuggingFace(messageDTO));
    }

    @PostMapping("rag")
    public ResponseEntity<String> chatRag(@RequestBody ChatMessageDTO messageDTO) {
        return ResponseEntity.ok().body(chatService.chatFromRAG(messageDTO));
    }
}
