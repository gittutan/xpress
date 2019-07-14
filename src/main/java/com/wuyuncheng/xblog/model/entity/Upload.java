package com.wuyuncheng.xblog.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Upload implements Serializable {
    private Integer uploadId;
    private Integer postId;
    private String filename;
    private String mimetype;
    private Integer size;
    private Integer created;
}
