package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuyuncheng.xpress.exception.AlreadyExistsException;
import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.model.dao.MetaDAO;
import com.wuyuncheng.xpress.model.dto.MetaDTO;
import com.wuyuncheng.xpress.model.dto.MetaDetailDTO;
import com.wuyuncheng.xpress.model.entity.Meta;
import com.wuyuncheng.xpress.model.entity.Relationship;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.model.param.EditMetaParam;
import com.wuyuncheng.xpress.model.param.MetaParam;
import com.wuyuncheng.xpress.service.MetaService;
import com.wuyuncheng.xpress.service.PostService;
import com.wuyuncheng.xpress.service.RelationshipService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaDAO metaDAO;
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private PostService postService;

    @Override
    public List<MetaDetailDTO> listMetas(MetaType metaType) {
        List<Meta> metas = metaDAO.selectList(new QueryWrapper<Meta>().eq("type", metaType.getValue()));
        List<MetaDetailDTO> metaDetailDTOList = new ArrayList<>();
        metas.stream()
                .forEach((meta) -> {
                    MetaDetailDTO metaDetailDTO = new MetaDetailDTO();
                    BeanUtils.copyProperties(meta, metaDetailDTO);
                    metaDetailDTOList.add(metaDetailDTO);
                });
        return metaDetailDTOList;
    }

    @Transactional
    @Override
    public void deleteMeta(Integer metaId, MetaType metaType) {
        metaMustExist(metaId, metaType);

        // 如果是分类，删除该分类下的文章
        if (metaType.getValue().equals(MetaType.CATEGORY.getValue())) {
            QueryWrapper<Relationship> queryWrapper = new QueryWrapper<Relationship>()
                    .eq("meta_id", metaId);
            // 获取该分类下的所有文章 ID
            List<Relationship> relationships = relationshipService.list(queryWrapper);
            Set<Integer> ids = new HashSet<>();
            relationships.forEach(item -> ids.add(item.getPostId()));
            // 删除对应的文章
            postService.removeByIds(ids);
            // 删除 Mete 表中对应的 Post
            relationshipService.removeByPostIds(ids);
        }
        // 如果是标签，删除 Relationship 表对应的数据
        if (metaType.getValue().equals(MetaType.TAG.getValue())) {
            QueryWrapper<Relationship> relationshipQueryWrapper = new QueryWrapper<Relationship>()
                    .eq("meta_id", metaId);
            relationshipService.remove(relationshipQueryWrapper);
        }

        // 删除 Meta 表中的数据
        int row = metaDAO.deleteById(metaId);
        Assert.state(row != 0, "删除失败");
    }

    @Override
    public void createMeta(MetaParam metaParam, MetaType metaType) {
        metaMustNotExist(metaParam.getName(), metaParam.getSlug());

        Meta meta = new Meta();
        BeanUtils.copyProperties(metaParam, meta);
        meta.setType(metaType.getValue());
        meta.setCount(0);
        int row = metaDAO.insert(meta);
        Assert.state(row != 0, metaType.getDescription() + "创建失败");
    }

    @Override
    public MetaDTO findMeta(Integer metaId, MetaType metaType) {
        QueryWrapper<Meta> queryWrapper = new QueryWrapper<Meta>()
                .eq("meta_id", metaId)
                .eq("type", metaType.getValue());
        Meta meta = metaDAO.selectOne(queryWrapper);
        if (null == meta) {
            throw new NotFoundException(metaType.getDescription() + "不存在");
        }
        MetaDTO metaDTO = new MetaDTO();
        BeanUtils.copyProperties(meta, metaDTO);
        return metaDTO;
    }

    @Override
    public void updateMeta(EditMetaParam editMetaParam, Integer metaId, MetaType metaType) {
        metaMustExist(metaId, metaType);

        Meta meta = new Meta();
        BeanUtils.copyProperties(editMetaParam, meta);
        meta.setMetaId(metaId);
        int row = metaDAO.updateById(meta);
        Assert.state(row != 0, metaType.getDescription() + "更新失败");
    }

    /**
     * Meta 不存在时抛出异常
     */
    private void metaMustExist(Integer metaId, MetaType metaType) {
        QueryWrapper<Meta> queryWrapper = new QueryWrapper<Meta>()
                .eq("meta_id", metaId)
                .eq("type", metaType.getValue());
        if (null == metaDAO.selectOne(queryWrapper)) {
            throw new NotFoundException("数据不存在");
        }
    }

    /**
     * Meta 存在时抛出异常
     */
    private void metaMustNotExist(String metaName, String metaSlug) {
        QueryWrapper<Meta> queryWrapper = new QueryWrapper<Meta>()
                .eq("name", metaName)
                .or()
                .eq("slug", metaSlug);
        Integer count = metaDAO.selectCount(queryWrapper);
        if (count != 0) {
            throw new AlreadyExistsException("数据已存在");
        }
    }

}
