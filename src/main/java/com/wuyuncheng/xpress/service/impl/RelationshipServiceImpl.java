package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyuncheng.xpress.model.dao.RelationshipDAO;
import com.wuyuncheng.xpress.model.entity.Relationship;
import com.wuyuncheng.xpress.service.RelationshipService;
import org.springframework.stereotype.Service;

@Service
public class RelationshipServiceImpl extends ServiceImpl<RelationshipDAO, Relationship> implements RelationshipService {
}
