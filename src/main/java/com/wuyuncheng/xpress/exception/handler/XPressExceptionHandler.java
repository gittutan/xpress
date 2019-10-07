package com.wuyuncheng.xpress.exception.handler;

import com.wuyuncheng.xpress.exception.BaseException;
import com.wuyuncheng.xpress.model.vo.MessageResponse;
import com.wuyuncheng.xpress.util.XPressUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class XPressExceptionHandler {

    /**
     * 处理参数效验器抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) throws IOException {
        // 如果是浏览器返回 HTML 页面
        if (XPressUtils.isBrowser()) {
            HttpServletResponse response = XPressUtils.getCurrentResponse().get();
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

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
     * 处理 XPress 所有的自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<MessageResponse> baseException(BaseException e) throws IOException {
        HttpStatus status = e.getStatus();
        // 如果是浏览器返回 HTML 页面
        if (XPressUtils.isBrowser()) {
            HttpServletResponse response = XPressUtils.getCurrentResponse().get();
            response.sendError(status.value());
        }

        return ResponseEntity
                .status(status)
                .body(MessageResponse.message(e.getMessage()));
    }

    /**
     * 处理未知异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> exception(Exception e) throws IOException {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        // 如果是浏览器返回 HTML 页面
        if (XPressUtils.isBrowser()) {
            HttpServletResponse response = XPressUtils.getCurrentResponse().get();
            response.sendError(status.value());
        }

        return ResponseEntity
                .status(status)
                .body(MessageResponse.message(e.getMessage()));
    }

}
