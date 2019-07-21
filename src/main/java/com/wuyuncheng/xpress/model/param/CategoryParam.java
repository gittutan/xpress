package com.wuyuncheng.xpress.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel
@Data
public class CategoryParam {

    @ApiModelProperty("分类名")
    @NotBlank(message = "分类名不能为空")
    @Size(max = 50, message = "分类名长度不能超过 {max} 字符")
    private String name;

    @ApiModelProperty("分类缩略名")
    @NotBlank(message = "分类缩略名不能为空")
    @Size(max = 50, message = "分类缩略名长度不能超过 {max} 字符")
    private String slug;

    @ApiModelProperty("分类描述")
    @Size(max = 200, message = "分类描述长度不能超过 {max} 字符")
    private String description;

}
