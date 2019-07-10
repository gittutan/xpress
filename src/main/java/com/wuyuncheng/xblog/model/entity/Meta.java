package com.wuyuncheng.xblog.model.entity;

import lombok.Data;

@Data
public class Meta {
    private Integer metaId;
    private String name;
    private String slug;
    private String type;
    private String description;
    private Integer count;
}
