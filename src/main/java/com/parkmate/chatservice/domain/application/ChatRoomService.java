package com.parkmate.chatservice.domain.application;

import com.parkmate.chatservice.domain.dto.request.ChatRoomCreateRequestDto;
import com.parkmate.chatservice.domain.dto.request.MarkMessageAsReadReqestDto;
import com.parkmate.chatservice.domain.dto.response.ChatRoomCreateResponseDto;

public interface ChatRoomService {

    ChatRoomCreateResponseDto createOrGetRoom(ChatRoomCreateRequestDto dto);

    void markUnreadMessagesAsRead(MarkMessageAsReadReqestDto dto);

}
