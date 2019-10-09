package com.wuyuncheng.xpress.model.param;

import com.wuyuncheng.xpress.model.entity.Upload;
import com.wuyuncheng.xpress.util.DateUtils;
import com.wuyuncheng.xpress.util.JWTUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class FileParam {

    @ApiModelProperty("上传文件")
    @NotNull(message = "请选择需要上传的文件")
    private MultipartFile file;

    public Upload convertTo() {
        String filename = DateUtils.nowUnix() + file.getOriginalFilename();
        Upload upload = new Upload();
        // 获取 Author ID
        Integer currentUserId = JWTUtils.getCurrentUserId();
        upload.setAuthorId(currentUserId);
        upload.setFilename(filename);
        upload.setMimetype(file.getContentType());
        upload.setSize(file.getSize());
        upload.setCreated(DateUtils.nowUnix());
        return upload;
    }

}
