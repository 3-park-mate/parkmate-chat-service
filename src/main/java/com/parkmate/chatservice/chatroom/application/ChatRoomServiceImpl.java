package com.parkmate.chatservice.chatroom.application;

import com.parkmate.chatservice.chatmessage.domain.ChatMessage;
import com.parkmate.chatservice.chatmessage.dto.request.MarkMessageAsReadReqestDto;
import com.parkmate.chatservice.chatroom.domain.ChatRoom;
import com.parkmate.chatservice.chatroom.dto.request.ChatRoomCreateRequestDto;
import com.parkmate.chatservice.chatroom.dto.response.ChatRoomCreateResponseDto;
import com.parkmate.chatservice.chatroom.infrastructure.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public ChatRoomCreateResponseDto createOrGetRoom(ChatRoomCreateRequestDto chatRoomCreateRequestDto) {
        Optional<ChatRoom> existingRoom = chatRoomRepository.findByParticipants(
                chatRoomCreateRequestDto.getParticipantAUuid(),
                chatRoomCreateRequestDto.getParticipantBUuid()
        );

        if (existingRoom.isPresent()) {
            ChatRoom room = existingRoom.get();
            return ChatRoomCreateResponseDto.builder()
                    .chatRoomUuid(room.getChatRoomUuid())
                    .build();
        }

        ChatRoom newRoom = chatRoomCreateRequestDto.toEntity();
        chatRoomRepository.save(newRoom);

        return ChatRoomCreateResponseDto.builder()
                .chatRoomUuid(newRoom.getChatRoomUuid())
                .build();
    }

    @Override
    public void markUnreadMessagesAsRead(MarkMessageAsReadReqestDto dto) {
        Query query = new Query(
                Criteria.where("chatRoomUuid").is(dto.getChatRoomUuid())
                        .and("receiverUuid").is(dto.getReceiverUuid())
                        .and("read").is(false)
        );

        Update update = new Update().set("read", true);
        mongoTemplate.updateMulti(query, update, ChatMessage.class);
    }

}
