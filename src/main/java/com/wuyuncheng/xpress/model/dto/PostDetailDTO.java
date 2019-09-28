package com.wuyuncheng.xpress.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostDetailDTO implements Serializable {

    private Integer postId;
    private String username;
    private String category;
    private String title;
    private String slug;
    private Integer modified;
    private Integer commentsCount;
    private String status;

}
