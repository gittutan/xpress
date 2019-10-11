package com.wuyuncheng.xpress.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyuncheng.xpress.config.XPressProperties;
import com.wuyuncheng.xpress.model.dto.MetaDTO;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.entity.Comment;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.service.CommentService;
import com.wuyuncheng.xpress.service.MetaService;
import com.wuyuncheng.xpress.service.PostService;
import com.wuyuncheng.xpress.util.XPressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController extends BaseController {

    @Autowired
    private XPressProperties properties;
    @Autowired
    private PostService postService;
    @Autowired
    private MetaService metaService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(Model model) {
        IPage<Post> page = new Page(1, properties.getPageSize());
        IPage<PostDTO> posts = postService.listPublishPosts(page);
        model.addAttribute("page", posts);
        return render("index", model);
    }

    @GetMapping("/posts")
    public String getPosts(@RequestParam(value = "p", required = false) Integer pageSize,
                       Model model) {
        if (null == pageSize) {
            return index(model);
        }
        IPage<Post> page = new Page(pageSize, properties.getPageSize());
        IPage<PostDTO> posts = postService.listPublishPosts(page);
        model.addAttribute("page", posts);
        return render("index", model);
    }

    @GetMapping("/post/{slugOrId}")
    public String getPost(@PathVariable String slugOrId,
                          Model model) {
        PostDTO post = postService.getPostBySlugOrId(slugOrId);
        String htmlContent = XPressUtils.markdownToHTML(post.getContent());
        post.setContent(htmlContent);
        model.addAttribute("post", post);
        List<MetaDTO> tags = metaService.listTagsByIds(post.getTagIds());
        model.addAttribute("tags", tags);
        List<Comment> comments = commentService.listApproveCommentsByPostId(post.getPostId());
        model.addAttribute("comments", comments);
        return render("post", model);
    }

}
