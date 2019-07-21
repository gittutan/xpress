package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> listCategories();
    void deleteCategory(Integer metaId);

}
