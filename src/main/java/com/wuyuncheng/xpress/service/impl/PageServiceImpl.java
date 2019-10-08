package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuyuncheng.xpress.exception.AlreadyExistsException;
import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.model.dao.PostDAO;
import com.wuyuncheng.xpress.model.dto.PageDTO;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.enums.PostType;
import com.wuyuncheng.xpress.model.param.PageParam;
import com.wuyuncheng.xpress.service.PageService;
import com.wuyuncheng.xpress.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PostDAO postDAO;

    @Override
    public List<PageDTO> listPages() {
        List<Post> posts = postDAO.selectList(
                new QueryWrapper<Post>()
                        .eq("type", PostType.PAGE.getValue())
        );
        List<PageDTO> pageDTOList = new ArrayList<>();
        for (Post post : posts) {
            PageDTO pageDTO = PageDTO.convertFrom(post);
            pageDTOList.add(pageDTO);
        }
        return pageDTOList;
    }

    @Transactional
    @Override
    public void removePage(Integer pageId) {
        pageMustExist(pageId);

        int row = postDAO.deleteById(pageId);
        Assert.state(row != 0, "页面删除失败");
    }

    @Transactional
    @Override
    public void createPage(PageParam pageParam) {
        // 判断页面 slug 是否已经存在
        String slug = pageParam.getSlug();
        if (null != slug) {
            pageSlugMustNotExist(slug);
        }

        // 插入页面
        Post page = pageParam.convertTo();
        page.setCommentsCount(0);
        page.setCreated(DateUtils.nowUnix());
        int row = postDAO.insert(page);
        Assert.state(row != 0, "页面创建失败");
    }

    @Override
    public PageDTO getPage(Integer pageId) {
        Post page = postDAO.selectOne(
                new QueryWrapper<Post>()
                        .eq("post_id", pageId)
                        .eq("type", PostType.PAGE.getValue())
        );
        if (null == page) {
            throw new NotFoundException("页面未找到");
        }
        PageDTO pageDTO = PageDTO.convertFrom(page);
        return pageDTO;
    }

    @Transactional
    @Override
    public PageDTO getPageBySlugOrId(String slugOrId) {
        Post page = postDAO.selectOne(
                new QueryWrapper<Post>()
                        .eq("slug", slugOrId)
                        .eq("type", PostType.PAGE.getValue())
        );
        if (null == page) {
            Integer id = Integer.valueOf(slugOrId);
            page = postDAO.selectOne(
                    new QueryWrapper<Post>()
                            .eq("post_id", id)
                            .eq("type", PostType.PAGE.getValue())
            );
        }
        if (null == page) {
            throw new NotFoundException("页面未找到");
        }
        return PageDTO.convertFrom(page);
    }

    @Transactional
    @Override
    public void updatePage(PageParam pageParam, Integer pageId) {
        // 判断页面 slug 是否已经存在
        pageMustExist(pageId);
        Post post = postDAO.selectById(pageId);
        if (null != pageParam.getSlug()
                &&
                !pageParam.getSlug().equals(post.getSlug())) {
            pageSlugMustNotExist(pageParam.getSlug());
        }

        // 更新页面
        Post pageUpdate = pageParam.convertTo();
        pageUpdate.setPostId(pageId);
        postDAO.updateById(pageUpdate);
    }

    /**
     * 页面不存在时抛出异常
     */
    private Post pageMustExist(Integer pageId) {
        Post page = postDAO.selectOne(
                new QueryWrapper<Post>()
                        .eq("post_id", pageId)
                        .eq("type", PostType.PAGE.getValue())
        );
        if (null == page) {
            throw new NotFoundException("页面不存在");
        }
        return page;
    }

    /**
     * 页面存在时抛出异常
     */
    private void pageSlugMustNotExist(String pageSlug) {
        Integer count = postDAO.selectCount(
                new QueryWrapper<Post>()
                        .eq("slug", pageSlug)
        );
        if (count != 0) {
            throw new AlreadyExistsException("页面别名已存在");
        }
    }

}
