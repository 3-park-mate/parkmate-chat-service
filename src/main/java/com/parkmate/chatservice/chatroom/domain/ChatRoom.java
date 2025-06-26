package com.parkmate.chatservice.chatroom.domain;

import com.parkmate.chatservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat_room")
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("채팅방 UUID")
    @Column(name = "chat_room_uuid", nullable = false, length = 36)
    private String chatRoomUuid;

    @Comment("A 참가자 UUID")
    @Column(name = "participantA_uuid", nullable = false, length = 36)
    private String participantAUuid;

    @Comment("B 참가자 UUID")
    @Column(name = "participantB_uuid", nullable = false, length = 36)
    private String participantBUuid;

    @Column(name = "last_message", nullable = false)
    private String lastMessage;

    @Column(name = "last_message_time", nullable = false)
    private LocalDateTime lastMessageTime;

    @Builder
    private ChatRoom(String chatRoomUuid,
                     String participantAUuid,
                     String participantBUuid) {
        this.chatRoomUuid = chatRoomUuid;
        this.participantAUuid = participantAUuid;
        this.participantBUuid = participantBUuid;
    }

    public void updateLastMessage(String message,
                                  LocalDateTime localDateTime) {
        this.lastMessage = message;
        this.lastMessageTime = localDateTime;
    }

}
