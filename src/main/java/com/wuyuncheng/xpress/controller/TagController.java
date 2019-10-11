package com.wuyuncheng.xpress.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyuncheng.xpress.config.XPressProperties;
import com.wuyuncheng.xpress.model.dto.MetaDTO;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.entity.Meta;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.service.MetaService;
import com.wuyuncheng.xpress.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagController extends BaseController {

    @Autowired
    private MetaService metaService;
    @Autowired
    private PostService postService;
    @Autowired
    private XPressProperties properties;

    @GetMapping("/tags")
    public String listTags(Model model) {
        List<MetaDTO> tags = metaService.listMetas(MetaType.TAG);
        model.addAttribute("tags", tags);
        return render("tags", model);
    }

    @GetMapping("tag/{slug}")
    public String getPostsByTagAndPage(@PathVariable String slug,
                                       @RequestParam(value = "p", required = false, defaultValue = "1") Integer pageNum,
                                       Model model) {
        Meta tag = metaService.getMetaBySlug(slug, MetaType.TAG);
        model.addAttribute("tag", tag);
        IPage<Post> postPage = new Page<>(pageNum, properties.getPageSize());
        IPage<PostDTO> posts = postService.listPublishPostsByTagId(tag.getMetaId(), postPage);
        model.addAttribute("page", posts);
        return render("tag", model);
    }

}
