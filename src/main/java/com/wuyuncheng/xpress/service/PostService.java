package com.wuyuncheng.xpress.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.dto.PostDetailDTO;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.param.PostParam;

import java.util.List;

public interface PostService extends IService<Post> {

    List<PostDetailDTO> listPosts();
    void deletePost(Integer postId);
    void createPost(PostParam postParam);
    PostDTO findPost(Integer postId);
    void updatePost(PostParam postParam, Integer metaId);

}
