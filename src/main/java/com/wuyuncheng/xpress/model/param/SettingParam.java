package com.wuyuncheng.xpress.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class SettingParam {

    @ApiModelProperty("网站标题")
    private String title;

    @ApiModelProperty("网站描述")
    private String description;

    @ApiModelProperty("网站关键词")
    private String keywords;

    @ApiModelProperty("网站导航栏HTML")
    private String navbar;

}
