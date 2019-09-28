package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listComments();
    void removeComment(Integer id);
    void reviewComment(Integer id);

}
