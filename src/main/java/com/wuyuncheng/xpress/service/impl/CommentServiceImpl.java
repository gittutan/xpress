package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.exception.ServiceException;
import com.wuyuncheng.xpress.model.dao.CommentDAO;
import com.wuyuncheng.xpress.model.entity.Comment;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.enums.CommentStatus;
import com.wuyuncheng.xpress.model.param.CommentParam;
import com.wuyuncheng.xpress.service.CommentService;
import com.wuyuncheng.xpress.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private PostService postService;

    @Override
    public IPage<Comment> listComments(IPage<Comment> page) {
        return commentDAO.selectPage(page,
                new QueryWrapper<Comment>()
                        .orderByDesc("created")
        );
    }

    @Override
    public List<Comment> listApproveCommentsByPostId(Integer postId) {
        List<Comment> comments = commentDAO.selectList(
                new QueryWrapper<Comment>()
                        .eq("post_id", postId)
                        .eq("status", CommentStatus.APPROVE.getValue())
        );
        return comments;
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

    @Override
    public void createComment(CommentParam commentParam) {
        Post post = postService.getById(commentParam.getPostId());
        if (!post.getIsAllowComments()) {
            throw new ServiceException("此文章禁止评论");
        }
        Comment comment = commentParam.convertTo();
        int row = commentDAO.insert(comment);
        Assert.state(row != 0, "评论创建失败");
    }

    private Comment commentMustExist(Integer id) {
        Comment comment = commentDAO.selectById(id);
        if (null == comment) {
            throw new NotFoundException("评论不存在");
        }
        return comment;
    }

}
