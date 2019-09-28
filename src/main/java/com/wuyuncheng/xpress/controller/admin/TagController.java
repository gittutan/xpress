package com.wuyuncheng.xpress.controller.admin;

import com.wuyuncheng.xpress.model.dto.MetaDTO;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.model.param.MetaParam;
import com.wuyuncheng.xpress.service.MetaService;
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
public class TagController {

    @Autowired
    private MetaService metaService;

    @ApiOperation("获取标签列表")
    @GetMapping("/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<MetaDTO> listTags() {
        return metaService.listMetas(MetaType.TAG);
    }

    @ApiOperation("删除标签")
    @DeleteMapping("/tags/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable Integer id) {
        Assert.notNull(id, "标签 ID 不能为空");
        metaService.deleteMeta(id, MetaType.TAG);
    }

    @ApiOperation("创建标签")
    @PostMapping("/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createTag(@Valid MetaParam metaParam) {
        metaService.createMeta(metaParam, MetaType.TAG);
        return MessageResponse.message("标签创建成功");
    }

    @ApiOperation("更新标签")
    @PutMapping("/tags/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse updateTag(@Valid MetaParam metaParam, @PathVariable Integer id) {
        Assert.notNull(id, "标签 ID 不能为空");
        metaService.updateMeta(metaParam, id, MetaType.TAG);
        return MessageResponse.message("标签更新成功");
    }

    @ApiOperation("获取单个标签")
    @GetMapping("/tags/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MetaDTO getTag(@PathVariable Integer id) {
        Assert.notNull(id, "标签 ID 不能为空");
        return metaService.findMeta(id, MetaType.TAG);
    }

}
