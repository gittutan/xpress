package com.wuyuncheng.xpress.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyuncheng.xpress.model.entity.Relationship;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipDAO extends BaseMapper<Relationship> {
    List<Integer> selectTagIdsByPostId(Integer postId);
    List<Integer> selectPostIdsByTagId(Integer tagId);
}
