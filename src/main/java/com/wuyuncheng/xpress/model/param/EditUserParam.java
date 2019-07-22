package com.wuyuncheng.xpress.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@ApiModel
@Data
public class EditUserParam {

//    @ApiModelProperty(value = "用户 ID", example = "123")
//    @NotNull(message = "用户 ID 不能为空")
//    transient private Integer userId;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Size(max = 50, message = "用户名长度不能超过 {max} 字符")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 255, message = "用户密码长度不能小于 {min} 或超过 {max} 字符")
    private String password;

    @ApiModelProperty("邮箱")
    @NotBlank(message = "Email 不能为空")
    @Email(message = "请输入正确的 Email")
    private String mail;

    @ApiModelProperty("网站")
    @NotBlank(message = "URL 不能为空")
    @Size(max = 100, message = "URL 长度不能超过 {max}")
    @Pattern(regexp = "^((ht|f)tps?):\\/\\/([\\w-]+(\\.[\\w-]+)*\\/?)+(\\?([\\w\\-\\.,@?^=%&:\\/~\\+#]*)+)?$", message = "请输入合法的URL")
    private String url;

    @ApiModelProperty("昵称")
    @NotBlank(message = "昵称不能为空")
    @Size(max = 50, message = "用户昵称长度不能超过 {max}")
    private String nickname;

}
