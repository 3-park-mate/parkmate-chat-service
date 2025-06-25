package com.parkmate.chatservice.domain.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendChatMessageReqVo {

    private String chatRoomUuid;
    private String senderUuid;
    private String receiverUuid;
    private String content;

    @Builder
    public SendChatMessageReqVo(String chatRoomUuid, String senderUuid, String receiverUuid, String content) {
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.content = content;
    }
}

