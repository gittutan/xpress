package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyuncheng.xpress.model.dao.PostDAO;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.dto.PostDetailDTO;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.param.PostParam;
import com.wuyuncheng.xpress.service.AdminService;
import com.wuyuncheng.xpress.service.MetaService;
import com.wuyuncheng.xpress.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostDAO, Post> implements PostService {

    @Autowired
    private PostDAO postDAO;
    @Autowired
    private MetaService metaService;
    @Autowired
    private AdminService adminService;

    @Override
    public List<PostDetailDTO> listPosts() {
        return postDAO.selectPostDetail();
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public void createPost(PostParam postParam) {

    }

    @Override
    public PostDTO findPost(Integer postId) {
        return null;
    }

    @Override
    public void updatePost(PostParam postParam, Integer metaId) {

    }

}
