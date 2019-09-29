package com.wuyuncheng.xpress.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyuncheng.xpress.model.entity.Relationship;

import java.util.List;

public interface RelationshipService extends IService<Relationship> {

    List<Integer> listTagIdsByPostId(Integer postId);

}
