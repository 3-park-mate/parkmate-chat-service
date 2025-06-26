package com.parkmate.chatservice.chatmessage.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatMessageGetResponseVo {

    private String chatRoomUuid;
    private String senderUuid;
    private String receiverUuid;
    private String content;
    private LocalDateTime sentAt;

    @Builder
    private ChatMessageGetResponseVo(String chatRoomUuid, String senderUuid, String receiverUuid, String content, LocalDateTime sentAt) {
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.content = content;
        this.sentAt = sentAt;
    }

}
