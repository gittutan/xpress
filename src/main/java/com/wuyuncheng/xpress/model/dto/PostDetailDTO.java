package com.wuyuncheng.xpress.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonPropertyOrder({"id"})
@Data
public class PostDetailDTO implements Serializable {

    @JsonProperty("id")
    private Integer postId;
    private String username;
    private String category;
    private String title;
    private String slug;
    private Integer modified;
    private String password;
    private Integer commentsCount;
    private String status;

}
