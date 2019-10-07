package com.wuyuncheng.xpress.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.param.PostParam;

public interface PostService extends IService<Post> {

    IPage<PostDTO> listPosts(Integer pageNum, Integer pageSize);
    void removePost(Integer postId);
    void createPost(PostParam postParam);
    PostDTO getPost(Integer postId);
    PostDTO getPostBySlugOrId(String slugOrId);
    void updatePost(PostParam postParam, Integer postId);

}
