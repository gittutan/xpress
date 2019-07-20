package com.wuyuncheng.xpress.controller.admin;

import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.model.dto.UserDTO;
import com.wuyuncheng.xpress.model.dto.UserDetailDTO;
import com.wuyuncheng.xpress.model.entity.User;
import com.wuyuncheng.xpress.model.param.EditUserParam;
import com.wuyuncheng.xpress.model.param.LoginParam;
import com.wuyuncheng.xpress.model.param.UserParam;
import com.wuyuncheng.xpress.model.vo.AuthToken;
import com.wuyuncheng.xpress.service.AdminService;
import com.wuyuncheng.xpress.util.MessageResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @ApiOperation("用户列表")
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDetailDTO> listUserDetail() {
        return adminService.listUserDetail();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        Assert.notNull(id, "Id 不能为空");
        adminService.deleteUser(id);
    }

    @ApiOperation("获取单个用户")
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable Integer id) {
        Assert.notNull(id, "Id 不能为空");
        return adminService.findUser(id);
    }

    @ApiOperation("更新用户")
    @PutMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse> updateUser(@Valid EditUserParam editUserParam) {
        int row = adminService.updateUser(editUserParam);
        if (0 == row) {
            throw new NotFoundException("该用户不存在");
        }
        return ResponseEntity.ok()
                .body(MessageResponse.message("用户信息更新成功"));
    }

}
