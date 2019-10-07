package com.wuyuncheng.xpress.controller.admin;

import com.wuyuncheng.xpress.model.dto.MetaDTO;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.model.param.MetaParam;
import com.wuyuncheng.xpress.service.MetaService;
import com.wuyuncheng.xpress.model.vo.MessageResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("ApiCategoryController")
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private MetaService metaService;

    @ApiOperation("获取分类列表")
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<MetaDTO> listCategories() {
        return metaService.listMetas(MetaType.CATEGORY);
    }

    @ApiOperation("删除分类")
    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCategory(@PathVariable Integer id) {
        metaService.removeMeta(id, MetaType.CATEGORY);
    }

    @ApiOperation("创建分类")
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createCategory(@RequestBody @Valid MetaParam metaParam) {
        metaService.createMeta(metaParam, MetaType.CATEGORY);
        return MessageResponse.message("分类创建成功");
    }

    @ApiOperation("更新分类")
    @PutMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse updateCategory(@RequestBody @Valid MetaParam metaParam,
                                          @PathVariable Integer id) {
        metaService.updateMeta(metaParam, id, MetaType.CATEGORY);
        return MessageResponse.message("分类更新成功");
    }

    @ApiOperation("获取单个分类")
    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MetaDTO getCategory(@PathVariable Integer id) {
        return metaService.getMeta(id, MetaType.CATEGORY);
    }

}
