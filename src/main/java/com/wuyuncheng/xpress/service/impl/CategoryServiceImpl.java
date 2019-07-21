package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuyuncheng.xpress.model.dao.MetaDAO;
import com.wuyuncheng.xpress.model.dto.CategoryDTO;
import com.wuyuncheng.xpress.model.entity.Meta;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private MetaDAO metaDAO;

    @Override
    public List<CategoryDTO> listCategories() {
        List<Meta> metas = metaDAO.selectList(new QueryWrapper<Meta>().eq("type", MetaType.CATEGORY.getValue()));
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        metas.stream()
                .forEach((meta) -> {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    BeanUtils.copyProperties(meta, categoryDTO);
                    categoryDTOList.add(categoryDTO);
                });
        return categoryDTOList;
    }

    @Override
    public void deleteCategory(Integer metaId) {
        metaDAO.deleteById(metaId);
    }

}
