package com.parkmate.chatservice.chatmessage.infrastructure;

import com.parkmate.chatservice.chatmessage.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String>, ChatMessageCustomRepository {

}