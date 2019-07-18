package com.wuyuncheng.xpress.util;

import lombok.Data;

@Data
public class MessageResponse {

    private String message;

    private MessageResponse() {
    }

    public static MessageResponse message(String message) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage(message);
        return messageResponse;
    }
}
