package com.wuyuncheng.xpress.controller.admin;

import com.wuyuncheng.xpress.model.dto.CategoryDTO;
import com.wuyuncheng.xpress.model.dto.CategoryDetailDTO;
import com.wuyuncheng.xpress.model.param.CategoryParam;
import com.wuyuncheng.xpress.model.param.EditCategoryParam;
import com.wuyuncheng.xpress.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取分类列表")
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDetailDTO> listCategories() {
        return categoryService.listCategories();
    }

    @ApiOperation("删除分类")
    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Integer id) {
        Assert.notNull(id, "分类 ID 不能为空");
        categoryService.deleteCategory(id);
    }

    @ApiOperation("创建分类")
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@Valid CategoryParam categoryParam) {
        categoryService.createCategory(categoryParam);
    }

    @ApiOperation("更新分类")
    @PutMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public void updateCategory(@Valid EditCategoryParam editCategoryParam) {
        categoryService.updateCategory(editCategoryParam);
    }

    @ApiOperation("获取单个分类")
    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable Integer id) {
        CategoryDTO categoryDTO = categoryService.findCategory(id);
        return categoryDTO;
    }

}
