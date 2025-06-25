package com.parkmate.chatservice.domain.presentation;

import com.parkmate.chatservice.common.response.ApiResponse;
import com.parkmate.chatservice.domain.application.ChatRoomService;
import com.parkmate.chatservice.domain.dto.request.ChatRoomCreateRequestDto;
import com.parkmate.chatservice.domain.dto.request.MarkMessageAsReadReqestDto;
import com.parkmate.chatservice.domain.vo.request.ChatRoomCreateRequestVo;
import com.parkmate.chatservice.domain.vo.response.ChatRoomCreateResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat-room")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    //채팅방 생성
    @PostMapping("/create")
    public ApiResponse<ChatRoomCreateResponseVo> createChatRoom(
            @RequestHeader("X-User-UUID") String participantAUuid,
            @RequestBody ChatRoomCreateRequestVo vo
    ) {

        ChatRoomCreateRequestDto dto = ChatRoomCreateRequestDto.of(participantAUuid, vo.getParticipantBUuid());
        return ApiResponse.ok(
//                HttpStatus.OK,
                chatRoomService.createOrGetRoom(dto).toVo());
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
