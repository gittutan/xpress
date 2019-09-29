package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyuncheng.xpress.model.dao.RelationshipDAO;
import com.wuyuncheng.xpress.model.entity.Relationship;
import com.wuyuncheng.xpress.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServiceImpl extends ServiceImpl<RelationshipDAO, Relationship> implements RelationshipService {

    @Autowired
    private RelationshipDAO relationshipDAO;

    @Override
    public List<Integer> listTagIdsByPostId(Integer postId) {
        return relationshipDAO.selectTagIdsByPostId(postId);
    }

}
