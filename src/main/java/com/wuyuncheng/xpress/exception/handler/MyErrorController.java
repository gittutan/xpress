package com.wuyuncheng.xpress.exception.handler;

import com.wuyuncheng.xpress.util.ErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

@ApiIgnore
@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ErrorResponse> error(HttpServletResponse response) {
        int statusCode = response.getStatus();
        return ResponseEntity
                .status(statusCode)
                .body(ErrorResponse.message(
                        HttpStatus.valueOf(statusCode).getReasonPhrase()
                ));
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
