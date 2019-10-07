package com.wuyuncheng.xpress.controller;

import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.service.PostService;
import com.wuyuncheng.xpress.util.XPressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController extends BaseController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/{slugOrId}")
    public String post(@PathVariable String slugOrId,
                       Model model) {
        PostDTO post = postService.getPostBySlugOrId(slugOrId);
        String htmlContent = XPressUtils.markdownToHTML(post.getContent());
        post.setContent(htmlContent);
        model.addAttribute("post", post);
        return render("post", model);
    }

}
