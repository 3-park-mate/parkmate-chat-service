package com.parkmate.chatservice.chatroom.presentation;

import com.parkmate.chatservice.chatmessage.dto.request.MarkMessageAsReadReqestDto;
import com.parkmate.chatservice.chatroom.application.ChatRoomService;
import com.parkmate.chatservice.chatroom.dto.request.ChatRoomCreateRequestDto;
import com.parkmate.chatservice.chatroom.vo.request.ChatRoomCreateRequestVo;
import com.parkmate.chatservice.chatroom.vo.response.ChatRoomCreateResponseVo;
import com.parkmate.chatservice.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat-room")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    //채팅방 생성
    @PostMapping
    public ApiResponse<ChatRoomCreateResponseVo> createChatRoom(
            @RequestHeader("X-User-UUID") String participantAUuid,
            @RequestBody ChatRoomCreateRequestVo chatRoomCreateRequestVo
    ) {

        ChatRoomCreateRequestDto chatRoomCreateRequestDto = ChatRoomCreateRequestDto.of(participantAUuid,
                chatRoomCreateRequestVo.getParticipantBUuid());

        return ApiResponse.ok(
                chatRoomService.createOrGetRoom(chatRoomCreateRequestDto).toVo());
    }

    //채팅방 읽음 처리
    @PatchMapping("/read/{chatRoomUuid}")
    public ApiResponse<String> readChatMessage(
            @RequestHeader("X-User-UUID") String receiverUuid,
            @PathVariable String chatRoomUuid
    ) {
        chatRoomService.markUnreadMessagesAsRead(MarkMessageAsReadReqestDto.of(receiverUuid, chatRoomUuid));
        return ApiResponse.of(
                HttpStatus.OK,
                "읽음 처리가 완료되었습니다. ",
                null
        );
    }

}
