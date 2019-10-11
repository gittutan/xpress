package com.wuyuncheng.xpress.controller;

import com.wuyuncheng.xpress.model.dto.PageDTO;
import com.wuyuncheng.xpress.model.entity.Comment;
import com.wuyuncheng.xpress.service.CommentService;
import com.wuyuncheng.xpress.service.PageService;
import com.wuyuncheng.xpress.util.XPressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PageController extends BaseController {

    @Autowired
    private PageService pageService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/page/{slugOrId}")
    public String getPage(@PathVariable String slugOrId,
                          Model model) {
        PageDTO page = pageService.getPageBySlugOrId(slugOrId);
        String htmlContent = XPressUtils.markdownToHTML(page.getContent());
        page.setContent(htmlContent);
        model.addAttribute("post", page);
        List<Comment> comments = commentService.listApproveCommentsByPostId(page.getPostId());
        model.addAttribute("comments", comments);
        return render("post", model);
    }

}
