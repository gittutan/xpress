package com.wuyuncheng.xpress.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.param.PostParam;
import com.wuyuncheng.xpress.service.PostService;
import com.wuyuncheng.xpress.model.vo.MessageResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("ApiPostController")
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @ApiOperation("获取文章列表")
    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public IPage<PostDTO> listPosts(@RequestParam Integer page,
                                    @RequestParam Integer size) {
        return postService.listPosts(page, size);
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable Integer id) {
        postService.removePost(id);
    }

    @ApiOperation("创建文章")
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createPost(@RequestBody @Valid PostParam postParam) {
        postService.createPost(postParam);
        return MessageResponse.message("文章创建成功");
    }

    @ApiOperation("更新文章")
    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse updatePost(@RequestBody @Valid PostParam postParam,
                                      @PathVariable Integer id) {
        postService.updatePost(postParam, id);
        return MessageResponse.message("文章更新成功");
    }

    @ApiOperation("获取单篇文章")
    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO getPost(@PathVariable Integer id) {
        return postService.getPost(id);
    }

}
