package com.parkmate.chatservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatRoomUuid;

    private String participantAUuid;

    private String participantBUuid;

    private String lastMessage;

    private LocalDateTime lastMessageTime;

    @Builder
    public ChatRoom(String chatRoomUuid, String participantAUuid, String participantBUuid) {
        this.chatRoomUuid = chatRoomUuid;
        this.participantAUuid = participantAUuid;
        this.participantBUuid = participantBUuid;
    }

    public void updateLastMessage(String message, LocalDateTime time) {
        this.lastMessage = message;
        this.lastMessageTime = time;
    }

}
