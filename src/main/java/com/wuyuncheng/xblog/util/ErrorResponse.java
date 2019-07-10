package com.wuyuncheng.xblog.util;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;

    private ErrorResponse() {
    }

    public static ErrorResponse message(String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        return errorResponse;
    }
}
