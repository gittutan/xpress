package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuyuncheng.xpress.exception.AlreadyExistsException;
import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.model.dao.PostDAO;
import com.wuyuncheng.xpress.model.dto.PageDTO;
import com.wuyuncheng.xpress.model.dto.PageDetailDTO;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.enums.PostType;
import com.wuyuncheng.xpress.model.param.PageParam;
import com.wuyuncheng.xpress.service.PageService;
import com.wuyuncheng.xpress.util.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PostDAO postDAO;

    @Override
    public List<PageDetailDTO> listPages() {
        return postDAO.selectPageDetail();
    }

    @Override
    public void deletePage(Integer pageId) {
        pageMustExist(pageId);

        int row = postDAO.deleteById(pageId);
        Assert.state(row != 0, "页面删除失败");
    }

    @Override
    public void createPage(PageParam pageParam) {
        String slug = pageParam.getSlug();
        if (null != slug) {
            pageMustNotExist(slug);
        }

        Post page = new Post();
        BeanUtils.copyProperties(pageParam, page);
        page.setType(PostType.PAGE.getValue());
        page.setCommentsCount(0);
        page.setCreated(DateUtils.nowUnix());
        page.setModified(DateUtils.nowUnix());
        int row = postDAO.insert(page);
        Assert.state(row != 0, "页面创建失败");
    }

    @Override
    public PageDTO findPage(Integer pageId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>()
                .eq("post_id", pageId)
                .eq("type", PostType.PAGE.getValue());
        Post page = postDAO.selectOne(queryWrapper);
        if (null == page) {
            throw new NotFoundException("页面未找到");
        }
        PageDTO pageDTO = new PageDTO();
        BeanUtils.copyProperties(page, pageDTO);
        return pageDTO;
    }

    @Override
    public void updatePage(PageParam pageParam, Integer pageId) {
        pageMustExist(pageId);

        Post page = new Post();
        BeanUtils.copyProperties(pageParam, page);
        page.setPostId(pageId);
        page.setModified(DateUtils.nowUnix());
        postDAO.updateById(page);
    }

    /**
     * 页面不存在时抛出异常
     */
    private Post pageMustExist(Integer pageId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>()
                .eq("post_id", pageId)
                .eq("type", PostType.PAGE.getValue());
        Post page = postDAO.selectOne(queryWrapper);
        if (null == page) {
            throw new NotFoundException("页面不存在");
        }
        return page;
    }

    /**
     * 页面存在时抛出异常
     */
    private void pageMustNotExist(String pageSlug) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>()
                .eq("slug", pageSlug);
        Integer count = postDAO.selectCount(queryWrapper);
        if (count != 0) {
            throw new AlreadyExistsException("页面已存在");
        }
    }

}
