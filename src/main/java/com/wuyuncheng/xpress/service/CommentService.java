package com.wuyuncheng.xpress.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuyuncheng.xpress.model.entity.Comment;
import com.wuyuncheng.xpress.model.param.CommentParam;

import java.util.List;

public interface CommentService {

    IPage<Comment> listComments(IPage<Comment> page);
    List<Comment> listApproveCommentsByPostId(Integer postId);
    void removeComment(Integer id);
    void approveComment(Integer id);
    void createComment(CommentParam commentParam);

}
