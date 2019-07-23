package com.wuyuncheng.xpress.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Post implements Serializable {

    @TableId
    private Integer postId;
    private String status;
    private String type;
    private String password;
    private Integer authorId;
    private Integer categoryId;
    private String title;
    private String content;
    private String slug;
    private Integer commentsCount;
    private Boolean isAllowComments;
    private Integer created;
    private Integer modified;

}
