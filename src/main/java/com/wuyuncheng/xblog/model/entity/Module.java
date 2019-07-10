package com.wuyuncheng.xblog.model.entity;

import lombok.Data;

@Data
public class Module {
    private Integer moduleId;
    private String name;
    private String slug;
    private String content;
    private String htmlId;
    private String type;
    private Boolean isHideTitle;
}
