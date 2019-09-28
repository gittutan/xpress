package com.wuyuncheng.xpress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyuncheng.xpress.exception.AlreadyExistsException;
import com.wuyuncheng.xpress.exception.NotFoundException;
import com.wuyuncheng.xpress.model.dao.PostDAO;
import com.wuyuncheng.xpress.model.dto.PostDTO;
import com.wuyuncheng.xpress.model.entity.Meta;
import com.wuyuncheng.xpress.model.entity.Post;
import com.wuyuncheng.xpress.model.entity.Relationship;
import com.wuyuncheng.xpress.model.enums.MetaType;
import com.wuyuncheng.xpress.model.enums.PostType;
import com.wuyuncheng.xpress.model.param.PostParam;
import com.wuyuncheng.xpress.service.MetaService;
import com.wuyuncheng.xpress.service.PostService;
import com.wuyuncheng.xpress.service.RelationshipService;
import com.wuyuncheng.xpress.util.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl extends ServiceImpl<PostDAO, Post> implements PostService {

    @Autowired
    private PostDAO postDAO;
    @Autowired
    private MetaService metaService;
    @Autowired
    private RelationshipService relationshipService;

    @Override
    public List<PostDTO> listPosts() {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>()
                .eq("type", PostType.POST.getValue());
        List<Post> posts = postDAO.selectList(queryWrapper);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }

    @Transactional
    @Override
    public void removePost(Integer postId) {
        Post post = postMustExist(postId);

        // meta 表分类 count 减1
        Integer metaId = post.getCategoryId();
        metaService.decrementCountById(metaId);
        // 删除 Tags
        deleteTagsByPostId(postId);
        // 删除 post 表中的文章
        int row = postDAO.deleteById(postId);
        Assert.state(row != 0, "文章删除失败");
    }

    @Transactional
    @Override
    public void createPost(PostParam postParam) {
        String slug = postParam.getSlug();
        if (null != slug) {
            postMustNotExist(slug);
        }

        // 待插入文章实体类
        Post post = new Post();
        BeanUtils.copyProperties(postParam, post);
        post.setType(PostType.POST.getValue());
        post.setCommentsCount(0);
        post.setCreated(DateUtils.nowUnix());
        post.setModified(DateUtils.nowUnix());

        // 插入文章到文章表
        int row = postDAO.insert(post);
        Assert.state(row != 0, "文章创建失败");
        if (null == postParam.getTags()) {
            return;
        }
        // 创建 Tags
        createTagsByPostId(postParam.getTags(), post.getPostId());
    }

    @Override
    public PostDTO getPost(Integer postId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>()
                .eq("post_id", postId)
                .eq("type", PostType.POST.getValue());
        Post post = postDAO.selectOne(queryWrapper);
        if (null == post) {
            throw new NotFoundException("文章未找到");
        }
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        return postDTO;
    }

    @Override
    public void updatePost(PostParam postParam, Integer postId) {
        postMustExist(postId);

        // 更新文章
        Post post = new Post();
        BeanUtils.copyProperties(postParam, post);
        post.setPostId(postId);
        post.setModified(DateUtils.nowUnix());
        postDAO.updateById(post);

        /**
         * 更新标签
         * 先删除标签，再创建标签
         */
        deleteTagsByPostId(postId);
        createTagsByPostId(postParam.getTags(), postId);
    }

    /**
     * 文章不存在时抛出异常
     */
    private Post postMustExist(Integer postId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>()
                .eq("post_id", postId)
                .eq("type", PostType.POST.getValue());
        Post post = postDAO.selectOne(queryWrapper);
        if (null == post) {
            throw new NotFoundException("文章不存在");
        }
        return post;
    }

    /**
     * 文章存在时抛出异常
     */
    private void postMustNotExist(String postSlug) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<Post>()
                .eq("slug", postSlug);
        Integer count = postDAO.selectCount(queryWrapper);
        if (count != 0) {
            throw new AlreadyExistsException("文章已存在");
        }
    }

    /**
     * 根据文章 ID 创建 Tags，Tags 必须已经存在
     */
    private void createTagsByPostId(Set<String> tags, Integer postId) {
        /**
         * 判断标签是否重复
         * 重复抛出异常
         * 不重复记录标签 ID
         */
        List<Meta> metas = metaService.list(new QueryWrapper<Meta>().eq("type", MetaType.TAG.getValue()));
        List<Integer> metaIds = new ArrayList<>();
        metas.stream()
                .forEach(meta -> {
                    String tagName = meta.getName();
                    if (tags.contains(tagName)) {
                        metaIds.add(meta.getMetaId());
                    } else {
//                        throw new NotFoundException("标签: " + tagName + " 不存在");
                    }
                });
        if (metaIds.size() != tags.size()) {
            throw new NotFoundException("请添加已存在的标签");
        }
        // 标签 count + 1
        metaService.incrementCountByName(tags);
        // 在 relationship 表中添加关系
        List<Relationship> relationships = new ArrayList<>();
        for (Integer meteId : metaIds) {
            Relationship relationship = new Relationship();
            relationship.setPostId(postId);
            relationship.setMetaId(meteId);
            relationships.add(relationship);
        }
        relationshipService.saveBatch(relationships);
    }

    /**
     * 根据文章 ID 删除 Tags
     */
    private void deleteTagsByPostId(Integer postId) {
        // meta 表标签 count 减1
        metaService.decrementCountByPostId(postId);
        // 删除 relationship 表中标签的关联数据
        relationshipService.remove(new QueryWrapper<Relationship>().eq("post_id", postId));
    }

}
