package com.wuyuncheng.xpress.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.param.PostParam;

public interface PostService extends IService<Post> {

    IPage<PostDTO> listPosts(IPage<Post> page);
    IPage<PostDTO> listPublishPosts(IPage<Post> page);
    IPage<PostDTO> listPublishPostsByCategoryId(Integer categoryId, IPage<Post> page);
    IPage<PostDTO> listPublishPostsByTagId(Integer tagId, IPage<Post> page);
    void removePost(Integer postId);
    void createPost(PostParam postParam);
    PostDTO getPost(Integer postId);
    PostDTO getPostBySlugOrId(String slugOrId);
    void updatePost(PostParam postParam, Integer postId);

}
