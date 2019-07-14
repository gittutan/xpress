package com.wuyuncheng.xblog.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Module implements Serializable {
    private Integer moduleId;
    private String name;
    private String slug;
    private String content;
    private String htmlId;
    private String type;
    private Boolean isHideTitle;
}
