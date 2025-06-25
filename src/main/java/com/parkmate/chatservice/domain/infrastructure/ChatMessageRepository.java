package com.parkmate.chatservice.domain.infrastructure;

import com.parkmate.chatservice.domain.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
