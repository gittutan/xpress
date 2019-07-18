package com.wuyuncheng.xpress.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Post implements Serializable {
    private Integer postId;
    private String status;
    private String type;
    private String password;
    private Integer authorId;
    private String title;
    private String content;
    private String slug;
    private Integer commentsCount;
    private Boolean isAllowComments;
    private Integer created;
    private Integer modified;
}