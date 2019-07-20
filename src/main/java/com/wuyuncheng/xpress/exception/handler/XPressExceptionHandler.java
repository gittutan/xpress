package com.wuyuncheng.xpress.exception.handler;

import com.wuyuncheng.xpress.exception.BaseException;
import com.wuyuncheng.xpress.util.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class XPressExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<MessageResponse> bindException(BindingResult bindingResult) {
        String msg = bindingResult.getFieldError().getDefaultMessage();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageResponse.message(msg));
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<MessageResponse> baseException(BaseException e) {
        HttpStatus status = e.getStatus();
        return ResponseEntity
                .status(status)
                .body(MessageResponse.message(e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponse> illegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageResponse.message(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> exception() {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(MessageResponse.message(status.getReasonPhrase()));
    }

}
