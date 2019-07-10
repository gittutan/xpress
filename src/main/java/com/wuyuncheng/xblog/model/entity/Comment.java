package com.wuyuncheng.xblog.model.entity;

import lombok.Data;

@Data
public class Comment {
    private Integer commentId;
    private Integer postId;
    private String status;
    private String author;
    private String mail;
    private String url;
    private String content;
    private String ip;
    private String useragent;
    private Integer parent;
    private Integer created;
}
