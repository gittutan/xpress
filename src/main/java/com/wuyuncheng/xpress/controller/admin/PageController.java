package com.wuyuncheng.xpress.controller.admin;

import com.wuyuncheng.xpress.model.dto.PageDTO;
import com.wuyuncheng.xpress.model.dto.PageDetailDTO;
import com.wuyuncheng.xpress.model.param.PageParam;
import com.wuyuncheng.xpress.service.PageService;
import com.wuyuncheng.xpress.util.MessageResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PageController {

    @Autowired
    private PageService pageService;

    @ApiOperation("获取页面列表")
    @GetMapping("/pages")
    @ResponseStatus(HttpStatus.OK)
    public List<PageDetailDTO> listPosts() {
        return pageService.listPages();
    }

    @ApiOperation("删除页面")
    @DeleteMapping("/pages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Integer id) {
        Assert.notNull(id, "页面 ID 不能为空");
        pageService.deletePage(id);
    }

    @ApiOperation("创建页面")
    @PostMapping("/pages")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createPost(@Valid PageParam pageParam) {
        pageService.createPage(pageParam);
        return MessageResponse.message("页面创建成功");
    }

    @ApiOperation("更新页面")
    @PutMapping("/pages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse updatePost(@Valid PageParam pageParam, @PathVariable Integer id) {
        Assert.notNull(id, "页面 ID 不能为空");
        pageService.updatePage(pageParam, id);
        return MessageResponse.message("页面更新成功");
    }

    @ApiOperation("获取单个页面")
    @GetMapping("/pages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PageDTO getPost(@PathVariable Integer id) {
        Assert.notNull(id, "页面 ID 不能为空");
        return pageService.findPage(id);
    }

}
