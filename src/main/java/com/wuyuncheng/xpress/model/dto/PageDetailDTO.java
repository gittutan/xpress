package com.wuyuncheng.xpress.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PageDetailDTO {

    @JsonProperty("id")
    private Integer postId;
    private String username;
    private String title;
    private String slug;
    private Integer modified;
    private Integer commentsCount;
    private String status;

}
