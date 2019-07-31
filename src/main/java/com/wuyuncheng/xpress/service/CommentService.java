package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.dto.CommentDetailDTO;

import java.util.List;

public interface CommentService {

    List<CommentDetailDTO> listComments();
    void deleteComment(Integer id);
    void reviewComment(Integer id);

}
