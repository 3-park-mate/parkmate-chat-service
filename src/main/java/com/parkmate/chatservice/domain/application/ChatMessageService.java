package com.parkmate.chatservice.domain.application;

import com.parkmate.chatservice.domain.dto.request.SendChatMessageReqDto;
import com.parkmate.chatservice.domain.vo.response.SendChatMessageResDto;

public interface ChatMessageService {

    SendChatMessageResDto sendMessage(SendChatMessageReqDto dto);

}
