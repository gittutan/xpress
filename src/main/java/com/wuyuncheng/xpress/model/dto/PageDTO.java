package com.wuyuncheng.xpress.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.service.AdminService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
public class PageDTO {

    private static AdminService adminService;

    @JsonIgnore
    @Autowired
    private AdminService adminServiceTemp;

    @PostConstruct
    public void init() {
        PageDTO.adminService = adminServiceTemp;
    }

    @JsonProperty("id")
    private Integer postId;
    private String status;
    @JsonIgnore
    private Integer authorId;
    private UserDTO author;
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
        UserDTO author = adminService.getUser(pageDTO.getAuthorId());
        pageDTO.setAuthor(author);
        return pageDTO;
    }

}
