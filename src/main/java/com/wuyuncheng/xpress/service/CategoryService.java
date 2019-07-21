package com.wuyuncheng.xpress.service;

import com.wuyuncheng.xpress.model.dto.CategoryDTO;
import com.wuyuncheng.xpress.model.dto.CategoryDetailDTO;
import com.wuyuncheng.xpress.model.param.CategoryParam;
import com.wuyuncheng.xpress.model.param.EditCategoryParam;

import java.util.List;

public interface CategoryService {

    List<CategoryDetailDTO> listCategories();
    void deleteCategory(Integer metaId);
    void createCategory(CategoryParam categoryParam);
    CategoryDTO findCategory(Integer metaId);
    void updateCategory(EditCategoryParam editCategoryParam);

}
