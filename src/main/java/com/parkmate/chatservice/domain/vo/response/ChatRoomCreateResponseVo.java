package com.parkmate.chatservice.domain.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomCreateResponseVo {

    private String ChatRoomUuid;

    @Builder
    public ChatRoomCreateResponseVo(String chatRoomUuid) {
        ChatRoomUuid = chatRoomUuid;
    }
}
