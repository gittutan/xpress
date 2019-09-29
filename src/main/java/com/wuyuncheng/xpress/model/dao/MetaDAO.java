package com.wuyuncheng.xpress.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyuncheng.xpress.model.entity.Meta;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MetaDAO extends BaseMapper<Meta> {

    int decrementCountById(Integer metaId);
    int decrementCountByPostId(Integer postId);
    int incrementCountByName(Collection<String> metaNames);
    List<Meta> selectTagsByIds(List<Integer> ids);

}
