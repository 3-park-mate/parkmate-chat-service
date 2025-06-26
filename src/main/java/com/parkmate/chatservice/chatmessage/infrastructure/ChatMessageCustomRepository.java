package com.parkmate.chatservice.chatmessage.infrastructure;

import com.parkmate.chatservice.common.response.CursorPage;
import com.parkmate.chatservice.chatmessage.dto.request.ChatMessageGetRequestDto;
import com.parkmate.chatservice.chatmessage.domain.ChatMessage;

public interface ChatMessageCustomRepository {

    CursorPage<ChatMessage> findChatMessagesByCursor(ChatMessageGetRequestDto chatMessageGetRequestDto);

}
