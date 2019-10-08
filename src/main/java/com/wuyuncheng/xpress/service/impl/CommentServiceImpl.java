package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.model.dao.CommentDAO;
import com.wuyuncheng.xpress.model.entity.Comment;
import com.wuyuncheng.xpress.model.enums.CommentStatus;
import com.wuyuncheng.xpress.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public IPage<Comment> listComments(IPage<Comment> page) {
        return commentDAO.selectPage(page, new QueryWrapper<>());
    }

    @Transactional
    @Override
    public void removeComment(Integer id) {
        commentMustExist(id);

        int row = commentDAO.deleteById(id);
        Assert.state(row != 0, "删除失败");
    }

    @Transactional
    @Override
    public void approveComment(Integer id) {
        commentMustExist(id);

        Comment comment = new Comment();
        comment.setCommentId(id);
        comment.setStatus(CommentStatus.APPROVE.getValue());
        commentDAO.updateById(comment);
    }

    private Comment commentMustExist(Integer id) {
        Comment comment = commentDAO.selectById(id);
        if (null == comment) {
            throw new NotFoundException("评论不存在");
        }
        return comment;
    }

}
