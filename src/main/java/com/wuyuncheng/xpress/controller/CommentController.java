package com.wuyuncheng.xpress.controller;

import com.wuyuncheng.xpress.model.param.CommentParam;
import com.wuyuncheng.xpress.model.vo.MessageResponse;
import com.wuyuncheng.xpress.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Controller
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createComment(@RequestBody @Valid CommentParam commentParam) {
        commentService.createComment(commentParam);
        return MessageResponse.message("评论提交成功");
    }

}
