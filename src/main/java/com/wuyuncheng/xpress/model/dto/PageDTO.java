package com.wuyuncheng.xpress.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wuyuncheng.xpress.model.entity.Post;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class PageDTO {

    @JsonProperty("id")
    private Integer postId;
    private String status;
    private Integer authorId;
    private String title;
    private String content;
    private String slug;
    private Boolean isAllowComments;
    private Integer commentsCount;
    @JsonProperty("timestamp")
    private Integer modified;

    public static PageDTO convertFrom(Post page) {
        PageDTO pageDTO = new PageDTO();
        BeanUtils.copyProperties(page, pageDTO);
        return pageDTO;
    }

}
