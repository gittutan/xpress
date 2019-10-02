package com.wuyuncheng.xpress.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Comment implements Serializable {

    @JsonProperty("id")
    @TableId
    private Integer commentId;
    private Integer postId;
    private String status;
    private String author;
    private String mail;
    private String url;
    private String content;
    private String ip;
    private String useragent;
    @JsonProperty("timestamp")
    private Integer created;

}
