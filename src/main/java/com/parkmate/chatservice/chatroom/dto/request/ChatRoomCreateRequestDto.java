package com.parkmate.chatservice.chatroom.dto.request;

import com.parkmate.chatservice.chatroom.domain.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ChatRoomCreateRequestDto {

    private String participantAUuid;
    private String participantBUuid;

    @Builder
    public ChatRoomCreateRequestDto(String participantAUuid,
                                    String participantBUuid) {
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
