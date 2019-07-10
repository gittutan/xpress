package com.wuyuncheng.xblog.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginParam {

    @NotBlank(message = "用户名不能为空")
    @Size(max = 50, message = "用户名长度不能超过 {max}")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(max = 255, message = "用户密码长度不能超过 {max}")
    private String password;

}
