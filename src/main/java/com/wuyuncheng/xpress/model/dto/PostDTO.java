package com.wuyuncheng.xpress.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostDTO implements Serializable {

    private String status;
    private String password;
    private Integer authorId;
    private String title;
    private String content;
    private String slug;
    private Boolean isAllowComments;

}
