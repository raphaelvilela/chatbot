package com.raphaelvilela.chatbot.dto;

import java.time.LocalDateTime;

public record  ChatMessageDTO (String message, LocalDateTime sentAt) {
}
