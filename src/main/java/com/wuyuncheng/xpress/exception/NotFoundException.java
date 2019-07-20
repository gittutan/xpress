package com.wuyuncheng.xpress.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BadRequestException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
