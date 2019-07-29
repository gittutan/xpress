package com.wuyuncheng.xpress.exception;

import org.springframework.http.HttpStatus;

public class SaveFileException extends ServiceException {

    public SaveFileException(String message) {
        super(message);
    }

    public SaveFileException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INSUFFICIENT_STORAGE;
    }

}
