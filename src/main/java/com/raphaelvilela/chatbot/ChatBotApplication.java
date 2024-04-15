package com.raphaelvilela.chatbot;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.raphaelvilela.chatbot.dto.ChatMessageDTO;
import com.raphaelvilela.chatbot.service.ChatService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ChatBotApplication {

    Logger logger = LoggerFactory.getLogger(ChatBotApplication.class);

	@Autowired
	ChatService aiAssistanceService;

	public static void main(String[] args) {
		SpringApplication.run(ChatBotApplication.class, args);
	}

	@PostConstruct
	public void test(){
		logger.info("INICIANDO RAG CHATBOT ######################################");
		logger.info("$$$$$ " + aiAssistanceService.chatFromRAG(new ChatMessageDTO("Give me all Marlon Brando movies ?", LocalDateTime.now())) + " $$$$$$");
		logger.info("TERMINANDO ######################################");
		
		logger.info("INICIANDO HuggingFace CHATBOT ######################################");
		logger.info("$$$$$ " + aiAssistanceService.chatFromHuggingFace(new ChatMessageDTO("How are you?", LocalDateTime.now())) + " $$$$$$");
		logger.info("TERMINANDO ######################################");
	}
}
