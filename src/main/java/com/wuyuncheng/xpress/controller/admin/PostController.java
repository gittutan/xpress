package com.wuyuncheng.xpress.controller.admin;

import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.dto.PostDetailDTO;
import com.wuyuncheng.xpress.model.param.PostParam;
import com.wuyuncheng.xpress.service.PostService;
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
public class PostController {

    @Autowired
    private PostService postService;

    @ApiOperation("获取文章列表")
    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDetailDTO> listPosts() {
        return postService.listPosts();
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Integer id) {
        Assert.notNull(id, "文章 ID 不能为空");
        postService.deletePost(id);
    }

    @ApiOperation("创建文章")
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createPost(@Valid PostParam postParam) {
        postService.createPost(postParam);
        return MessageResponse.message("文章创建成功");
    }

    @ApiOperation("更新文章")
    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse updatePost(@RequestBody @Valid PostParam postParam, @PathVariable Integer id) {
        Assert.notNull(id, "文章 ID 不能为空");
        postService.updatePost(postParam, id);
        return MessageResponse.message("文章更新成功");
    }

    @ApiOperation("获取单篇文章")
    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO getPost(@PathVariable Integer id) {
        Assert.notNull(id, "文章 ID 不能为空");
        return postService.findPost(id);
    }

}
