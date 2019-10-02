package com.wuyuncheng.xpress.model.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wuyuncheng.xpress.model.entity.Upload;
import com.wuyuncheng.xpress.service.AdminService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Component
@Data
public class UploadDTO implements Serializable {

    private static AdminService adminService;

    @JsonIgnore
    @Autowired
    private AdminService adminServiceTemp;

    @PostConstruct
    public void init() {
        UploadDTO.adminService = adminServiceTemp;
    }

    @JsonProperty("id")
    @TableId
    private Integer uploadId;
    private UserDTO author;
    private String filename;
    private String mimetype;
    private Long size;
    @JsonProperty("timestamp")
    private Integer created;

    public static UploadDTO convertFrom(Upload upload) {
        UploadDTO uploadDTO = new UploadDTO();
        BeanUtils.copyProperties(upload, uploadDTO);
        UserDTO author = adminService.getUser(upload.getAuthorId());
        uploadDTO.setAuthor(author);
        return uploadDTO;
    }
}
