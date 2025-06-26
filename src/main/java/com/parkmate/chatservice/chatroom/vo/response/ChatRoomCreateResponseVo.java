package com.parkmate.chatservice.chatroom.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomCreateResponseVo {

    private String ChatRoomUuid;

    @Builder
    private ChatRoomCreateResponseVo(String chatRoomUuid) {
        ChatRoomUuid = chatRoomUuid;
    }

}
