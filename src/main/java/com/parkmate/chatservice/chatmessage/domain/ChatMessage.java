package com.parkmate.chatservice.chatmessage.domain;

import com.parkmate.chatservice.common.entity.BaseEntity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_messages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage extends BaseEntity {

    @Id
    private String id;
    private String chatRoomUuid;
    private String senderUuid;
    private String receiverUuid;
    private String content;
    private LocalDateTime sentAt;
    private Boolean isRead;

    @Builder
    private ChatMessage(String id, String chatRoomUuid, String senderUuid, String receiverUuid,
                       String content, LocalDateTime sentAt, Boolean isRead) {
        this.id = id;
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.content = content;
        this.sentAt = sentAt;
        this.isRead = isRead;
    }

}