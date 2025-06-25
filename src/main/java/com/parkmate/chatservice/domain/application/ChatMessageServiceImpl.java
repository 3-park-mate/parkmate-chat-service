package com.parkmate.chatservice.domain.application;

import com.parkmate.chatservice.domain.dto.request.SendChatMessageReqDto;
import com.parkmate.chatservice.domain.infrastructure.ChatMessageRepository;
import com.parkmate.chatservice.domain.infrastructure.ChatRoomRepository;
import com.parkmate.chatservice.domain.vo.response.SendChatMessageResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

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
}
