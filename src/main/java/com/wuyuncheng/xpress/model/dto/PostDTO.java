package com.wuyuncheng.xpress.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.service.AdminService;
import com.wuyuncheng.xpress.service.MetaService;
import com.wuyuncheng.xpress.service.RelationshipService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@Component
@Data
public class PostDTO implements Serializable {

    private static AdminService adminService;
    private static MetaService metaService;
    private static RelationshipService relationshipService;

    @JsonIgnore
    @Autowired
    private AdminService adminServiceTemp;
    @JsonIgnore
    @Autowired
    private MetaService metaServiceTemp;
    @JsonIgnore
    @Autowired
    private RelationshipService relationshipServiceTemp;

    @PostConstruct
    public void init() {
        PostDTO.adminService = adminServiceTemp;
        PostDTO.metaService = metaServiceTemp;
        PostDTO.relationshipService = relationshipServiceTemp;
    }

    @JsonProperty("id")
    private Integer postId;
    private String status;
    @JsonIgnore
    private Integer authorId;
    private UserDTO author;
    @JsonIgnore
    private Integer categoryId;
    private MetaDTO category;
    @JsonProperty("tags")
    private List<Integer> tagIds;
    private String title;
    private String content;
    private String slug;
    private Boolean isAllowComments;
    private Integer commentsCount;
    @JsonProperty("timestamp")
    private Integer modified;

    public static PostDTO convertFrom(Post post) {
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        UserDTO author = adminService.getUser(postDTO.getAuthorId());
        postDTO.setAuthor(author);
        MetaDTO category = metaService.getMeta(postDTO.getCategoryId(), MetaType.CATEGORY);
        postDTO.setCategory(category);
        List<Integer> tagIds = relationshipService.listTagIdsByPostId(postDTO.getPostId());
        postDTO.setTagIds(tagIds);
        return postDTO;
    }

}
