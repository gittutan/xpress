package com.wuyuncheng.xpress.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyuncheng.xpress.config.XPressProperties;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private PostService postService;
    @Autowired
    private XPressProperties properties;

    @GetMapping("/")
    public String index(Model model) {
        IPage<Post> page = new Page(1, properties.getPageSize());
        IPage<PostDTO> posts = postService.listPublishPosts(page);
        model.addAttribute("page", posts);
        return render("index", model);
    }

    @GetMapping("/page/{pageSize}")
    public String page(@PathVariable Integer pageSize,
                       Model model) {
        IPage<Post> page = new Page(pageSize, properties.getPageSize());
        IPage<PostDTO> posts = postService.listPublishPosts(page);
        model.addAttribute("page", posts);
        return render("index", model);
    }

}
