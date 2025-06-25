package com.parkmate.chatservice.domain.presentation;

import com.parkmate.chatservice.domain.application.ChatMessageService;
import com.parkmate.chatservice.domain.dto.request.SendChatMessageReqDto;
import com.parkmate.chatservice.domain.vo.request.SendChatMessageReqVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatMessageController {

//    private final ChatMessageService chatMessageService;
//
//    @MessageMapping("/sendMessage")
////    @SendTo("/topic")
//    @SendTo("/topic/{chatRoomUuid}")
////    public String sendMessage(
//    public String sendMessage(
////            @DestinationVariable Uuid
//            @Header("X-User-UUID") String senderUuid,
//            SendChatMessageReqVo vo
//    ) {
//        log.info(">>> 메시지 수신: {}", vo);
//        chatMessageService.sendMessage(SendChatMessageReqDto.of(senderUuid, vo));
//        return vo.getContent();
//    }


    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendMessage")
    public void sendMessage(
            @Header("X-User-UUID") String senderUuid,
            SendChatMessageReqVo vo
    ) {
        log.info(">>> 메시지 수신: {}", vo);
        chatMessageService.sendMessage(SendChatMessageReqDto.of(senderUuid, vo));

        // 동적으로 구독 경로를 지정해 메시지를 전달
        messagingTemplate.convertAndSend(
                "/topic/" + vo.getChatRoomUuid(),
                vo
        );
    }

}
