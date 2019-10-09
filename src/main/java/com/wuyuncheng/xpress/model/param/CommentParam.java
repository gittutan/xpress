package com.wuyuncheng.xpress.model.param;

import com.wuyuncheng.xpress.model.entity.Comment;
import com.wuyuncheng.xpress.model.enums.CommentStatus;
import com.wuyuncheng.xpress.util.DateUtils;
import com.wuyuncheng.xpress.util.XPressUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.*;

@Data
public class CommentParam {

    @NotNull(message = "文章 ID 不能为空")
    @Digits(integer = 20, fraction = 0)
    private Integer postId;

    @NotBlank(message = "评论作者不能为空")
    @Size(max = 20, message = "评论作者长度不能超过 {max} 字符")
    private String author;

    @NotBlank(message = "Email 不能为空")
    @Email(message = "请输入正确的 Email")
    private String mail;

    @NotBlank(message = "URL 不能为空")
    @Size(max = 100, message = "URL 长度不能超过 {max}")
    @Pattern(regexp = "^((ht|f)tps?):\\/\\/([\\w-]+(\\.[\\w-]+)*\\/?)+(\\?([\\w\\-\\.,@?^=%&:\\/~\\+#]*)+)?$", message = "请输入合法的URL")
    private String url;

    @NotBlank(message = "评论内容不能为空")
    private String content;

    public Comment convertTo() {
        Comment comment = new Comment();
        BeanUtils.copyProperties(this, comment);
        comment.setStatus(CommentStatus.WAITING.getValue());
        String ip = XPressUtils.getRequestIP();
        comment.setIp(ip);
        String userAgent = XPressUtils.getRequestUserAgent();
        comment.setUseragent(userAgent);
        comment.setCreated(DateUtils.nowUnix());
        return comment;
    }

}
