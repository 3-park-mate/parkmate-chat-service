package com.parkmate.chatservice.chatmessage.dto.response;

import com.parkmate.chatservice.chatmessage.domain.ChatMessage;
import com.parkmate.chatservice.chatmessage.vo.response.ChatMessageGetResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatMessageGetResponseDto {

    private String chatRoomUuid;
    private String senderUuid;
    private String receiverUuid;
    private String content;
    private LocalDateTime sentAt;

    @Builder
    private ChatMessageGetResponseDto(String chatRoomUuid, String senderUuid, String receiverUuid, String content, LocalDateTime sentAt) {
        this.chatRoomUuid = chatRoomUuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.content = content;
        this.sentAt = sentAt;
    }

    public static ChatMessageGetResponseDto from(ChatMessage chatMessage) {
        return ChatMessageGetResponseDto.builder()
                .chatRoomUuid(chatMessage.getChatRoomUuid())
                .senderUuid(chatMessage.getSenderUuid())
                .receiverUuid(chatMessage.getReceiverUuid())
                .content(chatMessage.getContent())
                .sentAt(chatMessage.getSentAt())
                .build();
    }

    public ChatMessageGetResponseVo toVo() {
        return ChatMessageGetResponseVo.builder()
                .chatRoomUuid(chatRoomUuid)
                .senderUuid(senderUuid)
                .receiverUuid(receiverUuid)
                .content(content)
                .sentAt(sentAt)
                .build();
    }

}
