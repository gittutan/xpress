package com.wuyuncheng.xpress.controller.admin;

import com.wuyuncheng.xpress.model.dto.UserDTO;
import com.wuyuncheng.xpress.model.param.LoginParam;
import com.wuyuncheng.xpress.model.param.UserParam;
import com.wuyuncheng.xpress.model.vo.AuthToken;
import com.wuyuncheng.xpress.service.AdminService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("登陆")
    @GetMapping("/login")
    public AuthToken login(@Valid LoginParam loginParam) {
        AuthToken token = adminService.auth(loginParam);
        return token;
    }

    @ApiOperation("创建帐号")
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@Valid UserParam userParam) {
        UserDTO userDTO = adminService.createUser(userParam);
        return userDTO;
    }

}
