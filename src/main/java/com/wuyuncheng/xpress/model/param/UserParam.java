package com.wuyuncheng.xpress.model.param;

import com.wuyuncheng.xpress.model.entity.User;
import com.wuyuncheng.xpress.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ApiModel
@Data
public class UserParam {

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

    public User convertTo() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        user.setCreated(DateUtils.nowUnix());
        String password = user.getPassword();
        String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(passwordMD5);
        return user;
    }

}
