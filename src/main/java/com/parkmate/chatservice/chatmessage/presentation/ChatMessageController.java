package com.parkmate.chatservice.chatmessage.presentation;

import com.parkmate.chatservice.common.response.ApiResponse;
import com.parkmate.chatservice.common.response.CursorPage;
import com.parkmate.chatservice.chatmessage.application.ChatMessageService;
import com.parkmate.chatservice.chatmessage.dto.request.ChatMessageGetRequestDto;
import com.parkmate.chatservice.chatmessage.dto.request.SendChatMessageReqDto;
import com.parkmate.chatservice.chatmessage.dto.response.ChatMessageGetResponseDto;
import com.parkmate.chatservice.chatmessage.vo.request.ChatMessageGetRequestVo;
import com.parkmate.chatservice.chatmessage.vo.request.SendChatMessageReqVo;
import com.parkmate.chatservice.chatmessage.vo.response.ChatMessageGetResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
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
        chatMessageService.sendMessage(SendChatMessageReqDto.of(senderUuid, vo));

        // 동적으로 구독 경로를 지정해 메시지를 전달
        messagingTemplate.convertAndSend(
                "/topic/" + vo.getChatRoomUuid(),
                vo
        );
    }

    @GetMapping("/{chatRoomUuid}/message")
    public ApiResponse<CursorPage<ChatMessageGetResponseVo>> getChatMessages(
            @PathVariable("chatRoomUuid") String chatRoomUuid,
            @ModelAttribute ChatMessageGetRequestVo chatMessageGetRequestVo,
            @RequestHeader("X-User-UUID") String senderUuid
    ) {
        log.info(">>> 메시지 수신: {}", chatMessageGetRequestVo);
        return ApiResponse.ok(
                chatMessageService.getChatMessage(
                                ChatMessageGetRequestDto.of(chatRoomUuid, chatMessageGetRequestVo, senderUuid))
                        .map(ChatMessageGetResponseDto::toVo)
        );

    }

}
