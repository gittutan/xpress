package com.wuyuncheng.xpress.controller.admin;

import com.wuyuncheng.xpress.model.dto.CategoryDTO;
import com.wuyuncheng.xpress.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取分类列表")
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> listCategories() {
        return categoryService.listCategories();
    }
}
