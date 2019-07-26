package com.wuyuncheng.xpress.model.dto;

import lombok.Data;

@Data
public class PageDTO {

    private String status;
    private Integer authorId;
    private String title;
    private String content;
    private String slug;
    private Boolean isAllowComments;
    private Integer modified;

}
