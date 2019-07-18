package com.wuyuncheng.xblog.exception.handler;

import com.wuyuncheng.xblog.exception.AuthException;
import com.wuyuncheng.xblog.exception.LoginException;
import com.wuyuncheng.xblog.util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity bindException(BindingResult bindingResult) {
        String msg = bindingResult.getFieldError().getDefaultMessage();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.message(msg));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity SQLIntegrityConstraintViolationException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.message("用户名重复"));
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity loginException(LoginException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.message(e.getMessage()));
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity authException(AuthException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.message(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exception() {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(ErrorResponse.message(status.getReasonPhrase()));
    }

}
