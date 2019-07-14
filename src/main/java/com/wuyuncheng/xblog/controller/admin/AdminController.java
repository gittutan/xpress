package com.wuyuncheng.xblog.controller.admin;

import com.wuyuncheng.xblog.config.Constant;
import com.wuyuncheng.xblog.model.dto.UserDTO;
import com.wuyuncheng.xblog.model.param.LoginParam;
import com.wuyuncheng.xblog.model.param.UserParam;
import com.wuyuncheng.xblog.model.vo.AuthToken;
import com.wuyuncheng.xblog.service.AdminService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"用户操作接口"})
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

    @ApiOperation("注销")
    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(
            @ApiParam("token")
            @RequestHeader(Constant.HEADER_TOKEN) String token) {
        adminService.clearToken(token);
    }

    @ApiOperation("创建帐号")
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@Valid UserParam userParam) {
        UserDTO userDTO = adminService.createUser(userParam);
        return userDTO;
    }

}
