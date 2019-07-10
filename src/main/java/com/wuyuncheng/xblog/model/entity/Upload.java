package com.wuyuncheng.xblog.model.entity;

import lombok.Data;

@Data
public class Upload {
    private Integer uploadId;
    private Integer postId;
    private String filename;
    private String mimetype;
    private Integer size;
    private Integer created;
}
