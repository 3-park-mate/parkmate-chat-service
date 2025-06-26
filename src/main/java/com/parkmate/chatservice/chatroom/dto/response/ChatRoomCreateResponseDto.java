package com.parkmate.chatservice.chatroom.dto.response;

import com.parkmate.chatservice.chatroom.vo.response.ChatRoomCreateResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomCreateResponseDto {

    private String chatRoomUuid;

    @Builder
    private ChatRoomCreateResponseDto(String chatRoomUuid) {
        this.chatRoomUuid = chatRoomUuid;
    }

    public ChatRoomCreateResponseVo toVo() {
        return ChatRoomCreateResponseVo.builder()
                .chatRoomUuid(chatRoomUuid)
                .build();
    }
}
