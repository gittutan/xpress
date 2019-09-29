package com.wuyuncheng.xpress.model.param;

import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.enums.PostType;
import com.wuyuncheng.xpress.model.enums.annotation.ValidateString;
import com.wuyuncheng.xpress.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
@Data
public class PageParam {

    @ApiModelProperty("页面状态")
    @NotBlank(message = "页面状态不能为空")
    @ValidateString(acceptedValues = {"publish", "draft"})
    @Size(max = 50, message = "页面状态长度不能超过 {max}")
    private String status;

    @ApiModelProperty("页面作者 ID")
    @Digits(integer = 20, fraction = 0)
    private Integer authorId;

    @ApiModelProperty("页面标题")
    @NotBlank(message = "页面标题不能为空")
    @Size(max = 200, message = "文章标题长度不能超过 {max} 字符")
    private String title;

    @ApiModelProperty("页面内容")
    @NotBlank(message = "页面内容不能为空")
    private String content;

    @ApiModelProperty("页面缩略名")
    @Size(max = 200, message = "页面缩略名长度不能超过 {max} 字符")
    private String slug;

    @ApiModelProperty("是否允许评论")
    @NotNull(message = "是否允许评论不能为空")
    private Boolean isAllowComments;

    public Post convertTo() {
        Post page = new Post();
        BeanUtils.copyProperties(this, page);
        page.setType(PostType.PAGE.getValue());
        page.setModified(DateUtils.nowUnix());
        return page;
    }

}
