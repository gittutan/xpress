package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuyuncheng.xpress.exception.AlreadyExistsException;
import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.exception.ServiceException;
import com.wuyuncheng.xpress.model.dao.MetaDAO;
import com.wuyuncheng.xpress.model.dto.CategoryDTO;
import com.wuyuncheng.xpress.model.dto.CategoryDetailDTO;
import com.wuyuncheng.xpress.model.entity.Meta;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.model.param.CategoryParam;
import com.wuyuncheng.xpress.model.param.EditCategoryParam;
import com.wuyuncheng.xpress.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private MetaDAO metaDAO;

    @Override
    public List<CategoryDetailDTO> listCategories() {
        List<Meta> metas = metaDAO.selectList(new QueryWrapper<Meta>().eq("type", MetaType.CATEGORY.getValue()));
        List<CategoryDetailDTO> categoryDetailDTOList = new ArrayList<>();
        metas.stream()
                .forEach((meta) -> {
                    CategoryDetailDTO categoryDetailDTO = new CategoryDetailDTO();
                    BeanUtils.copyProperties(meta, categoryDetailDTO);
                    categoryDetailDTOList.add(categoryDetailDTO);
                });
        return categoryDetailDTOList;
    }

    @Override
    public void deleteCategory(Integer metaId) {
        categoryMustExist(metaId);

        int row = metaDAO.deleteById(metaId);
        if (row == 0) {
            throw new ServiceException("分类删除失败");
        }
    }

    @Override
    public void createCategory(CategoryParam categoryParam) {
        categoryMustNotExist(categoryParam.getName(), categoryParam.getSlug());

        Meta meta = new Meta();
        BeanUtils.copyProperties(categoryParam, meta);
        meta.setType(MetaType.CATEGORY.getValue());
        meta.setCount(0);
        int row = metaDAO.insert(meta);
        if (row == 0) {
            throw new ServiceException("分类创建失败");
        }
    }

    @Override
    public CategoryDTO findCategory(Integer metaId) {
        QueryWrapper<Meta> queryWrapper = new QueryWrapper<Meta>()
                .eq("meta_id", metaId)
                .eq("type", MetaType.CATEGORY.getValue());
        Meta meta = metaDAO.selectOne(queryWrapper);
        Assert.notNull(meta, "该分类不存在");
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(meta, categoryDTO);
        return categoryDTO;
    }

    @Override
    public void updateCategory(EditCategoryParam editCategoryParam) {
        categoryMustExist(editCategoryParam.getMetaId());

        Meta meta = new Meta();
        BeanUtils.copyProperties(editCategoryParam, meta);
        meta.setType(MetaType.CATEGORY.getValue());
        int row = metaDAO.updateById(meta);
        if (row == 0) {
            throw new ServiceException("分类更新失败");
        }
    }

    private void categoryMustExist(Integer metaId) {
        QueryWrapper<Meta> queryWrapper = new QueryWrapper<Meta>()
                .eq("meta_id", metaId);
        if (null == metaDAO.selectOne(queryWrapper)) {
            throw new NotFoundException("该分类不存在");
        }
    }

    private void categoryMustNotExist(String metaName, String metaSlug) {
        QueryWrapper<Meta> queryWrapper = new QueryWrapper<Meta>()
                .eq("name", metaName)
                .eq("slug", metaSlug);
        if (null != metaDAO.selectOne(queryWrapper)) {
            throw new AlreadyExistsException("该分类已存在");
        }
    }

}
