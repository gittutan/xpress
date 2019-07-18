package com.wuyuncheng.xpress.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Meta implements Serializable {
    private Integer metaId;
    private String name;
    private String slug;
    private String type;
    private String description;
    private Integer count;
}
