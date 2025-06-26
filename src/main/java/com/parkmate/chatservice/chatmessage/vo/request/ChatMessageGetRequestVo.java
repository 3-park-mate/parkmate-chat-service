package com.parkmate.chatservice.chatmessage.vo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatMessageGetRequestVo {

    private String cursor;
    private Integer size;

}
