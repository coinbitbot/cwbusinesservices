package com.cwbusinesservices.mergers;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.PostCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.BlogCategoryEntity;
import com.cwbusinesservices.pojo.entities.PostEntity;
import com.cwbusinesservices.pojo.view.BlogCategoryView;
import com.cwbusinesservices.services.blog.IPostService;
import com.cwbusinesservices.services.utils.Utils;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 02.08.2017.
 */
@Component
public class BlogCategoryMerger implements Merger<BlogCategoryEntity,BlogCategoryView>{

    @Autowired
    private Utils utils;

    @Autowired
    private IPostService postService;

    @Override
    public void merge(BlogCategoryEntity entity, BlogCategoryView view) throws BaseException {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (utils.notEmpty(view.getName())) entity.setName(view.getName());
        else view.setName(entity.getName());
        if (utils.notEmpty(view.getCode())) entity.setCode(view.getCode());
        else view.setCode(entity.getCode());
        if (view.getPosition()!=null) entity.setPosition(view.getPosition());
        else view.setPosition(entity.getPosition());
        /*if(view.getPosts()!=null&&view.getPosts().size()>0){
            PostCriteria criteria = new PostCriteria();
            criteria.setIds(view.getPosts());
            List<PostEntity> posts = postService.getList(criteria);
            entity.setPosts(Sets.newHashSet(posts));
        }else if (entity.getPosts()!=null){
            List<Integer> ids = new ArrayList<>();
            for (PostEntity post:entity.getPosts())
                ids.add(post.getId());
            view.setPosts(ids);
        }*/
    }
}
