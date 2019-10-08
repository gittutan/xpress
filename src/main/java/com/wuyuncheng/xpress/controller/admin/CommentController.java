package com.wuyuncheng.xpress.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyuncheng.xpress.model.entity.Comment;
import com.wuyuncheng.xpress.service.CommentService;
import com.wuyuncheng.xpress.model.vo.MessageResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("ApiCommentController")
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation("获取评论列表")
    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public IPage<Comment> listComments(@RequestParam("page") Integer pageNum,
                                       @RequestParam("size") Integer pageSize) {
        IPage<Comment> page = new Page<>(pageNum, pageSize);
        return commentService.listComments(page);
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeComment(@PathVariable Integer id) {
        commentService.removeComment(id);
    }

    @ApiOperation("设置评论通过审核")
    @PatchMapping("/comments/{id}/approve")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse approveComment(@PathVariable Integer id) {
        commentService.approveComment(id);
        return MessageResponse.message("评论审核成功");
    }

}
