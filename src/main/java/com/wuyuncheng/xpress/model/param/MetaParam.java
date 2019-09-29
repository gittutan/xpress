package com.wuyuncheng.xpress.model.param;

import com.wuyuncheng.xpress.model.entity.Meta;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel
@Data
public class MetaParam {

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

    public Meta convertTo() {
        Meta meta = new Meta();
        BeanUtils.copyProperties(this, meta);
        return meta;
    }

}
