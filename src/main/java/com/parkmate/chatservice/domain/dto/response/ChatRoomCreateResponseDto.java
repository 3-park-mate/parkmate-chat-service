package com.parkmate.chatservice.domain.dto.response;

import com.parkmate.chatservice.domain.vo.response.ChatRoomCreateResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomCreateResponseDto {

    private String chatRoomUuid;

    @Builder
    public ChatRoomCreateResponseDto(String chatRoomUuid) {
        this.chatRoomUuid = chatRoomUuid;
    }

    public ChatRoomCreateResponseVo toVo() {
        return ChatRoomCreateResponseVo.builder()
                .chatRoomUuid(chatRoomUuid)
                .build();
    }
}
