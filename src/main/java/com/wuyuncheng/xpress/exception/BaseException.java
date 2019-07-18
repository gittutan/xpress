package com.wuyuncheng.xpress.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract HttpStatus getStatus();

}
