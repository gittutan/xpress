package com.wuyuncheng.xblog.controller.admin;

import com.wuyuncheng.xblog.config.Constant;
import com.wuyuncheng.xblog.model.dto.UserDTO;
import com.wuyuncheng.xblog.model.param.LoginParam;
import com.wuyuncheng.xblog.model.param.UserParam;
import com.wuyuncheng.xblog.model.vo.AuthToken;
import com.wuyuncheng.xblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public ResponseEntity<AuthToken> login(@Valid LoginParam loginParam) {
        AuthToken token = adminService.auth(loginParam);
        return ResponseEntity
                .ok()
                .body(token);
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        String token = request.getHeader(Constant.HEADER_TOKEN);
        adminService.clearToken(token);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/users")
    public UserDTO createUser(@Valid UserParam userParam) {
        UserDTO userDTO = adminService.createUser(userParam);
        return userDTO;
    }

}
