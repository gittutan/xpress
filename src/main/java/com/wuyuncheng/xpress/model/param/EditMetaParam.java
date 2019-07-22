package com.wuyuncheng.xpress.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EditMetaParam {

//    @ApiModelProperty(value = "ID", example = "123")
//    @NotNull(message = "ID 不能为空")
//    private Integer metaId;

    @ApiModelProperty("名称")
    @NotBlank(message = "名称不能为空")
    @Size(max = 50, message = "名称长度不能超过 {max} 字符")
    private String name;

    @ApiModelProperty("缩略名")
    @NotBlank(message = "缩略名不能为空")
    @Size(max = 50, message = "缩略名长度不能超过 {max} 字符")
    private String slug;

    @ApiModelProperty("描述")
    @Size(max = 200, message = "描述长度不能超过 {max} 字符")
    private String description;

}
