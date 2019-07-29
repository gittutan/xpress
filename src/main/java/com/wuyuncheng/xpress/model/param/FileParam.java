package com.wuyuncheng.xpress.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class FileParam {

    @ApiModelProperty("用户 ID")
    @Digits(integer = 20, fraction = 0)
    private Integer authorId;

    @ApiModelProperty("上传文件")
    @NotNull(message = "请选择需要上传的文件")
    private MultipartFile file;

}
