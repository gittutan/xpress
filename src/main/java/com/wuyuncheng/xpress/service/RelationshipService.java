package com.wuyuncheng.xpress.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyuncheng.xpress.model.entity.Relationship;

import java.util.Collection;

public interface RelationshipService extends IService<Relationship> {

    boolean removeByPostIds(Collection<Integer> ids);

}
