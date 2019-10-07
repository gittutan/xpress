package com.wuyuncheng.xpress.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String index(@RequestParam(defaultValue = "10") Integer size,
                        Model model) {
        IPage<PostDTO> posts = postService.listPosts(1, size);
        model.addAttribute("page", posts);
        return render("index", model);
    }

    @GetMapping("/page/{page}")
    public String page(@PathVariable Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       Model model) {
        IPage<PostDTO> posts = postService.listPosts(page, size);
        model.addAttribute("page", posts);
        return render("index", model);
    }

}
