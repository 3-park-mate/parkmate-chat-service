package com.parkmate.chatservice.chatmessage.infrastructure;

import com.parkmate.chatservice.common.response.CursorPage;
import com.parkmate.chatservice.chatmessage.dto.request.ChatMessageGetRequestDto;
import com.parkmate.chatservice.chatmessage.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ChatMessageCustomRepositoryImpl implements ChatMessageCustomRepository {

    private final MongoTemplate mongoTemplate;

    private static final int DEFAULT_PAGE_SIZE = 10;

    @Override
    public CursorPage<ChatMessage> findChatMessagesByCursor(ChatMessageGetRequestDto chatMessageGetRequestDto) {

        int size = chatMessageGetRequestDto.getSize() != null ? chatMessageGetRequestDto.getSize() : DEFAULT_PAGE_SIZE;
        String cursor = chatMessageGetRequestDto.getCursor();
        Criteria criteria = Criteria.where("chatRoomUuid").is(chatMessageGetRequestDto.getChatRoomUuid());

        if (cursor != null) {
            criteria = criteria.and("_id").lt(chatMessageGetRequestDto.getCursor());
        }

        Query query = new Query(criteria)
                .with(Sort.by(Sort.Direction.DESC, "_id"))
                .limit(size + 1);

        List<ChatMessage> messages = mongoTemplate.find(query, ChatMessage.class);

        String nextCursor = null;
        boolean hasNext = false;
        if (messages.size() > size) {
            nextCursor = messages.get(size).getId();
            hasNext = true;
            messages.remove(size);
        }

        log.info("nextCursor{} ", nextCursor);
        log.info("size {} ", size);
        return CursorPage.of(messages, hasNext, nextCursor);
    }
}
