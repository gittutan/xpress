package com.wuyuncheng.xpress.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends BadRequestException {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }

}
