package com.wuyuncheng.xpress.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyuncheng.xpress.model.dto.MetaDTO;
import com.wuyuncheng.xpress.model.dto.PageDTO;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.service.MetaService;
import com.wuyuncheng.xpress.service.PageService;
import com.wuyuncheng.xpress.service.PostService;
import com.wuyuncheng.xpress.util.XPressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController extends BaseController {

    @Autowired
    private PageService pageService;

    @GetMapping("/{slugOrId}")
    public String getPage(@PathVariable String slugOrId,
                          Model model) {
        PageDTO page = pageService.getPageBySlugOrId(slugOrId);
        String htmlContent = XPressUtils.markdownToHTML(page.getContent());
        page.setContent(htmlContent);
        model.addAttribute("post", page);
        return render("post", model);
    }

}
