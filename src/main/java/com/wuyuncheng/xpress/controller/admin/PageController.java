package com.wuyuncheng.xpress.controller.admin;

import com.wuyuncheng.xpress.model.dto.PageDTO;
import com.wuyuncheng.xpress.model.param.PageParam;
import com.wuyuncheng.xpress.service.PageService;
import com.wuyuncheng.xpress.model.vo.MessageResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("ApiPageController")
@RequestMapping("/api")
public class PageController {

    @Autowired
    private PageService pageService;

    @ApiOperation("获取页面列表")
    @GetMapping("/pages")
    @ResponseStatus(HttpStatus.OK)
    public List<PageDTO> listPosts() {
        return pageService.listPages();
    }

    @ApiOperation("删除页面")
    @DeleteMapping("/pages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable Integer id) {
        pageService.removePage(id);
    }

    @ApiOperation("创建页面")
    @PostMapping("/pages")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createPost(@RequestBody @Valid PageParam pageParam) {
        pageService.createPage(pageParam);
        return MessageResponse.message("页面创建成功");
    }

    @ApiOperation("更新页面")
    @PutMapping("/pages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse updatePost(@RequestBody @Valid PageParam pageParam,
                                      @PathVariable Integer id) {
        pageService.updatePage(pageParam, id);
        return MessageResponse.message("页面更新成功");
    }

    @ApiOperation("获取单个页面")
    @GetMapping("/pages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PageDTO getPost(@PathVariable Integer id) {
        return pageService.getPage(id);
    }

}
