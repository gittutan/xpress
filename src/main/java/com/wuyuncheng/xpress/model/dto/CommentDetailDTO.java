package com.wuyuncheng.xpress.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDetailDTO implements Serializable {

    private Integer commentId;
    private Integer postId;
    private String postSlug;
    private String status;
    private String author;
    private String mail;
    private String url;
    private String content;
    private String ip;
    private String useragent;
    private Integer created;

}
