package com.parkmate.chatservice.domain.dto.request;

import com.parkmate.chatservice.domain.entity.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ChatRoomCreateRequestDto {

    private String chatRoomUuid;
    private String participantAUuid;
    private String participantBUuid;

    @Builder
    public ChatRoomCreateRequestDto(String chatRoomUuid,
                                    String participantAUuid,
                                    String participantBUuid) {
        this.chatRoomUuid = chatRoomUuid;
        this.participantAUuid = participantAUuid;
        this.participantBUuid = participantBUuid;
    }

    public static ChatRoomCreateRequestDto of(String participantAUuid, String participantBUuid) {
        return ChatRoomCreateRequestDto.builder()
                .participantAUuid(participantAUuid)
                .participantBUuid(participantBUuid)
                .build();
    }

    public ChatRoom toEntity() {
        return ChatRoom.builder()
                .chatRoomUuid(UUID.randomUUID().toString())
                .participantAUuid(participantAUuid)
                .participantBUuid(participantBUuid)
                .build();
    }
}
