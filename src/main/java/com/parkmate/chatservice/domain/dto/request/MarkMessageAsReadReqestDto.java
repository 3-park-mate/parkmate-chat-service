package com.parkmate.chatservice.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MarkMessageAsReadReqestDto {

    private String receiverUuid;
    private String chatRoomUuid;

    @Builder
    public MarkMessageAsReadReqestDto(String receiverUuid, String chatRoomUuid) {
        this.receiverUuid = receiverUuid;
        this.chatRoomUuid = chatRoomUuid;
    }

    public static MarkMessageAsReadReqestDto of(String receiverUuid, String chatRoomUuid) {
        return MarkMessageAsReadReqestDto.builder()
                .receiverUuid(receiverUuid)
                .chatRoomUuid(chatRoomUuid)
                .build();
    }
}
