package com.wuyuncheng.xpress.exception.handler;

import com.wuyuncheng.xpress.exception.BaseException;
import com.wuyuncheng.xpress.util.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class XPressExceptionHandler {

//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<MessageResponse> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(MessageResponse.message(e.getMessage()));
//    }
//
//    @ExceptionHandler(BindException.class)
//    public ResponseEntity<MessageResponse> bindException(BindingResult bindingResult) {
//        String msg = bindingResult.getFieldError().getDefaultMessage();
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(MessageResponse.message(msg));
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<MessageResponse> methodArgumentTypeMismatchException(NumberFormatException e) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(MessageResponse.message("参数错误: " + e.getMessage()));
//    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<MessageResponse> illegalStateException(IllegalStateException e) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(MessageResponse.message(e.getMessage()));
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<MessageResponse> illegalArgumentException(IllegalArgumentException e) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(MessageResponse.message(e.getMessage()));
//    }

    /**
     * 处理参数效验器抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError error : bindingResult.getFieldErrors()) {
            String msg = error.getDefaultMessage();
            String message = String.format("%s & ", msg);
            sb.append(message);
        }
        String result = sb.delete(sb.length() - 3, sb.length() - 1).toString();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MessageResponse.message(result));
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<MessageResponse> baseException(BaseException e) {
        HttpStatus status = e.getStatus();
        return ResponseEntity
                .status(status)
                .body(MessageResponse.message(e.getMessage()));
    }

    /**
     * 处理未知异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> exception() {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(MessageResponse.message(status.getReasonPhrase()));
    }

}
