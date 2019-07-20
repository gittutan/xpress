package com.wuyuncheng.xpress.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyuncheng.xpress.model.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO extends BaseMapper<Comment> {
}
