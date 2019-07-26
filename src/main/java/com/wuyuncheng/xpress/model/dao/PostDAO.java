package com.wuyuncheng.xpress.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyuncheng.xpress.model.dto.PageDetailDTO;
import com.wuyuncheng.xpress.model.dto.PostDetailDTO;
import com.wuyuncheng.xpress.model.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDAO extends BaseMapper<Post> {
    List<PostDetailDTO> selectPostDetail();
    List<PageDetailDTO> selectPageDetail();
}
