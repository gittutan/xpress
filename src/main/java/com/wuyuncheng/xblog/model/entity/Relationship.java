package com.wuyuncheng.xblog.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Relationship implements Serializable {
    private Integer postId;
    private Integer metaId;
}
