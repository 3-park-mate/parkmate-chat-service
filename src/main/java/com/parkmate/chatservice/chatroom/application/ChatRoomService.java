package com.parkmate.chatservice.chatroom.application;

import com.parkmate.chatservice.chatmessage.dto.request.MarkMessageAsReadReqestDto;
import com.parkmate.chatservice.chatroom.dto.request.ChatRoomCreateRequestDto;
import com.parkmate.chatservice.chatroom.dto.response.ChatRoomCreateResponseDto;

public interface ChatRoomService {

    ChatRoomCreateResponseDto createOrGetRoom(ChatRoomCreateRequestDto chatRoomCreateRequestDto);

    void markUnreadMessagesAsRead(MarkMessageAsReadReqestDto markMessageAsReadReqestDto);

}
