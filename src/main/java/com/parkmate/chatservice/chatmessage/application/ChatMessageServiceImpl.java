package com.parkmate.chatservice.chatmessage.application;

import com.parkmate.chatservice.common.response.CursorPage;
import com.parkmate.chatservice.chatmessage.dto.request.ChatMessageGetRequestDto;
import com.parkmate.chatservice.chatmessage.dto.request.SendChatMessageReqDto;
import com.parkmate.chatservice.chatmessage.dto.response.ChatMessageGetResponseDto;
import com.parkmate.chatservice.chatmessage.domain.ChatMessage;
import com.parkmate.chatservice.chatmessage.infrastructure.ChatMessageRepository;
import com.parkmate.chatservice.chatroom.infrastructure.ChatRoomRepository;
import com.parkmate.chatservice.chatmessage.vo.response.SendChatMessageResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    private static final int DEFAULT_PAGE_SIZE = 10;

    @Override
    public SendChatMessageResDto sendMessage(SendChatMessageReqDto dto) {
        SendChatMessageResDto result = SendChatMessageResDto.from(chatMessageRepository.save(dto.toEntity()));
        chatRoomRepository.findByChatRoomUuid(dto.getChatRoomUuid())
                .ifPresent(room -> {
                    room.updateLastMessage(dto.getContent(), dto.getSentAt());
                    chatRoomRepository.save(room);
                });
//        simpMessagingTemplate.convertAndSend("/queue/messages/" + dto.getChatRoomUuid(), dto);
        log.info("@@@@@ : " + dto.getChatRoomUuid());
        simpMessagingTemplate.convertAndSend("/topic/chat-room/" + dto.getChatRoomUuid(), dto);
        return result;
    }

    @Override
    public CursorPage<ChatMessageGetResponseDto> getChatMessage(ChatMessageGetRequestDto chatMessageGetRequestDto) {
        CursorPage<ChatMessage> page = chatMessageRepository.findChatMessagesByCursor(chatMessageGetRequestDto);

        List<ChatMessageGetResponseDto> dtoList = page.getContent().stream()
                .map(ChatMessageGetResponseDto::from)
                .toList();

        return CursorPage.of(dtoList, page.getHasNext(), page.getNextCursor());
    }

}
