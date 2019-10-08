package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyuncheng.xpress.exception.AlreadyExistsException;
import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.model.dao.MetaDAO;
import com.wuyuncheng.xpress.model.dto.MetaDTO;
import com.wuyuncheng.xpress.model.entity.Meta;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.entity.Relationship;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.model.param.MetaParam;
import com.wuyuncheng.xpress.service.MetaService;
import com.wuyuncheng.xpress.service.PostService;
import com.wuyuncheng.xpress.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

@Service
public class MetaServiceImpl extends ServiceImpl<MetaDAO, Meta> implements MetaService {

    @Autowired
    private MetaDAO metaDAO;
    @Autowired
    private RelationshipService relationshipService;
    @Autowired
    private PostService postService;

    @Override
    public List<MetaDTO> listMetas(MetaType metaType) {
        List<Meta> metas = metaDAO.selectList(
                new QueryWrapper<Meta>().eq("type", metaType.getValue())
        );
        return convertToMetaDTOList(metas);
    }

    @Override
    public List<MetaDTO> listTagsByIds(List<Integer> ids) {
        List<Meta> tags = metaDAO.selectTagsByIds(ids);
        return convertToMetaDTOList(tags);
    }

    @Transactional
    @Override
    public void removeMeta(Integer metaId, MetaType metaType) {
        metaMustExist(metaId, metaType);

        // 如果是分类，删除该分类下的文章
        if (MetaType.CATEGORY.getValue().equals(metaType.getValue())) {
            QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>().eq("category_id", metaId);
            List<Post> postsDelete = postService.list(queryWrapper);
            for (Post post : postsDelete) {
                // 被删除文章的标签 count - 1
                metaDAO.decrementCountByPostId(post.getPostId());
                // 删除 relationship 表中的数据
                relationshipService.remove(
                        new QueryWrapper<Relationship>()
                                .eq("post_id", post.getPostId())
                );
            }
            postService.remove(queryWrapper);
        }
        // 如果是标签，删除 Relationship 表对应的数据
        if (MetaType.TAG.getValue().equals(metaType.getValue())) {
            relationshipService.remove(
                    new QueryWrapper<Relationship>()
                            .eq("meta_id", metaId)
            );
        }
        // 删除 Meta 表中的数据
        int row = metaDAO.deleteById(metaId);
        Assert.state(row != 0, "删除失败");
    }

    @Transactional
    @Override
    public void createMeta(MetaParam metaParam, MetaType metaType) {
        metaMustNotExist(metaParam.getName(), metaParam.getSlug());

        Meta meta = metaParam.convertTo();
        meta.setType(metaType.getValue());
        meta.setCount(0);
        int row = metaDAO.insert(meta);
        Assert.state(row != 0, metaType.getDescription() + "创建失败");
    }

    @Override
    public MetaDTO getMeta(Integer metaId, MetaType metaType) {
        Meta meta = metaDAO.selectOne(
                new QueryWrapper<Meta>()
                        .eq("meta_id", metaId)
                        .eq("type", metaType.getValue())
        );
        if (null == meta) {
            throw new NotFoundException(metaType.getDescription() + "不存在");
        }
        return MetaDTO.convertFrom(meta);
    }

    @Override
    public Meta getMetaBySlug(String slug, MetaType metaType) {
        Meta meta = metaDAO.selectOne(
                new QueryWrapper<Meta>()
                        .eq("slug", slug)
                        .eq("type", metaType.getValue())
        );
        if (null == meta) {
            throw new NotFoundException(metaType.getDescription() + "不存在");
        }
        return meta;
    }

    @Transactional
    @Override
    public void updateMeta(MetaParam metaParam, Integer metaId, MetaType metaType) {
        metaMustExist(metaId, metaType);

        Meta meta = metaParam.convertTo();
        meta.setMetaId(metaId);
        int row = metaDAO.updateById(meta);
        Assert.state(row != 0, metaType.getDescription() + "更新失败");
    }

    @Override
    public boolean decrementCountById(Integer metaId) {
        return metaDAO.decrementCountById(metaId) != 0;
    }

    @Override
    public boolean decrementCountByPostId(Integer postId) {
        return metaDAO.decrementCountByPostId(postId) != 0;
    }

    @Override
    public boolean incrementCountById(Integer metaId) {
        return metaDAO.incrementCountById(metaId) != 0;
    }

    @Override
    public boolean incrementCountByIds(List<Integer> metaIds) {
        return metaDAO.incrementCountByIds(metaIds) != 0;
    }

    private List<MetaDTO> convertToMetaDTOList(Collection<Meta> metas) {
        List<MetaDTO> metaDTOList = new ArrayList<>();
        for (Meta meta : metas) {
            MetaDTO metaDTO = MetaDTO.convertFrom(meta);
            metaDTOList.add(metaDTO);
        }
        return metaDTOList;
    }

    /**
     * Meta 不存在时抛出异常
     */
    private Meta metaMustExist(Integer metaId, MetaType metaType) {
        QueryWrapper<Meta> queryWrapper = new QueryWrapper<Meta>()
                .eq("meta_id", metaId)
                .eq("type", metaType.getValue());
        Meta meta = metaDAO.selectOne(queryWrapper);
        if (null == meta) {
            throw new NotFoundException("数据不存在");
        }
        return meta;
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
