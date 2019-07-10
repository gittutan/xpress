package com.wuyuncheng.xblog.model.entity;

import lombok.Data;

@Data
public class Post {
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
