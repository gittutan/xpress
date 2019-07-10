package com.wuyuncheng.xblog.model.param;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserParam {

    @NotBlank(message = "用户名不能为空")
    @Size(max = 50, message = "用户名长度不能超过 {max}")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 255, message = "用户密码长度不能小于 {min} 或超过 {max}")
    private String password;

    @NotBlank(message = "Email不能为空")
    @Email(message = "请输入正确的Email")
    private String mail;

    @NotBlank(message = "URL不能为空")
    @Size(max = 100, message = "URL长度不能超过 {max}")
    @Pattern(regexp = "^((ht|f)tps?):\\/\\/([\\w-]+(\\.[\\w-]+)*\\/?)+(\\?([\\w\\-\\.,@?^=%&:\\/~\\+#]*)+)?$", message = "请输入合法的URL")
    private String url;

    @NotBlank(message = "昵称不能为空")
    @Size(max = 50, message = "用户昵称长度不能超过 {max}")
    private String nickname;

}
