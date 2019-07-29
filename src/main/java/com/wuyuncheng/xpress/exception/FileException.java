package com.wuyuncheng.xpress.exception;

import org.springframework.http.HttpStatus;

public class FileException extends ServiceException {

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INSUFFICIENT_STORAGE;
    }

}
