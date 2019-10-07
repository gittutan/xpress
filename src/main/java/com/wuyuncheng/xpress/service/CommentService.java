package com.wuyuncheng.xpress.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuyuncheng.xpress.model.entity.Comment;

public interface CommentService {

    IPage<Comment> listComments(Integer pageNum, Integer pageSize);
    void removeComment(Integer id);
    void approveComment(Integer id);

}
