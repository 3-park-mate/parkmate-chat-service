package com.parkmate.chatservice.chatmessage.application;

import com.parkmate.chatservice.common.response.CursorPage;
import com.parkmate.chatservice.chatmessage.dto.request.ChatMessageGetRequestDto;
import com.parkmate.chatservice.chatmessage.dto.request.SendChatMessageReqDto;
import com.parkmate.chatservice.chatmessage.dto.response.ChatMessageGetResponseDto;
import com.parkmate.chatservice.chatmessage.vo.response.SendChatMessageResDto;

public interface ChatMessageService {

    SendChatMessageResDto sendMessage(SendChatMessageReqDto dto);

    CursorPage<ChatMessageGetResponseDto> getChatMessage(ChatMessageGetRequestDto chatMessageGetRequestDto);

}
