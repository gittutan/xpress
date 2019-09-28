package com.wuyuncheng.xpress.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UploadDetailDTO implements Serializable {

    private Integer uploadId;
    private String username;
    private String filename;
    private String mimetype;
    private Long size;
    private Integer created;

}
