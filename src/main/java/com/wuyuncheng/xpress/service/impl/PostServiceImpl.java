package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyuncheng.xpress.model.dao.PostDAO;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends ServiceImpl<PostDAO, Post> implements PostService {
}
