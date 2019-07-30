package com.wuyuncheng.xpress.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class SettingParam {

    @ApiModelProperty("网站标题")
    @NotBlank(message = "网站标题不能为空")
    private String title;

    @ApiModelProperty("网站描述")
    @NotBlank(message = "网站描述不能为空")
    private String description;

    @ApiModelProperty("网站关键词")
    @NotBlank(message = "网站关键词不能为空")
    private String keywords;

}
