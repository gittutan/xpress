package com.wuyuncheng.xpress.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel
@Data
public class LoginParam {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名或密码不能为空")
    @Size(max = 50, message = "用户名长度不能超过 {max} 字符")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "用户名或密码不能为空")
    @Size(min = 6, max = 255, message = "用户密码长度不能小于 {min} 或超过 {max} 字符")
    private String password;

}
