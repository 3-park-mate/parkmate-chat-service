package com.parkmate.chatservice.chatmessage.dto.request;

import com.parkmate.chatservice.chatmessage.vo.request.ChatMessageGetRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageGetRequestDto {

    private String chatRoomUuid;
    private String senderUuid;
    private String cursor;
    private Integer size;

    @Builder
    private ChatMessageGetRequestDto(String chatRoomUuid, String senderUuid, String cursor, int size) {
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.cursor = cursor;
        this.size = size;
    }

    public static ChatMessageGetRequestDto of(String chatRoomUuid, ChatMessageGetRequestVo chatMessageGetRequestVo, String senderUuid) {
        return ChatMessageGetRequestDto.builder()
                .chatRoomUuid(chatRoomUuid)
                .senderUuid(senderUuid)
                .cursor(chatMessageGetRequestVo.getCursor())
                .size(chatMessageGetRequestVo.getSize())
                .build();
    }

}
