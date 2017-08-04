package com.cwbusinesservices.mergers;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.PostEntity;
import com.cwbusinesservices.pojo.view.PostView;
import com.cwbusinesservices.services.blog.IBlogCategoryService;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 02.08.2017.
 */
@Service
public class PostMerger implements Merger<PostEntity,PostView> {


    @Autowired
    private FileMerger fileMerger;
    @Autowired
    private Utils utils;

    @Autowired
    private IBlogCategoryService service;


    @Override
    public void merge(PostEntity entity, PostView view) throws BaseException {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        fileMerger.merge(view,entity);
        if (utils.notEmpty(view.getTitle())) entity.setTitle(view.getTitle());
        else view.setTitle(entity.getTitle());
        if(utils.notEmpty(view.getUrl())) entity.setUrl(view.getUrl());
        else view.setUrl(entity.getUrl());
        if(utils.notEmpty(view.getShort_description())) entity.setShortDescription(view.getShort_description());
        else view.setShort_description(entity.getShortDescription());
        if (utils.notEmpty(view.getDate_of_publication()))entity.setDate(Utils.convertDate(view.getDate_of_publication()));
        else if (entity.getDate() != null) view.setDate_of_publication(entity.getDate().toString());
        if(utils.notEmpty(view.getMeta_description()))entity.setMetaDescription(view.getMeta_description());
        else view.setMeta_description(entity.getMetaDescription());
        if(utils.notEmpty(view.getMeta_title())) entity.setMetaTitle(view.getMeta_title());
        else view.setMeta_title(entity.getMetaTitle());
        if(utils.notEmpty(view.getMeta_keywords())) entity.setMetaKeywords(view.getMeta_keywords());
        else view.setMeta_keywords(entity.getMetaKeywords());
        if(utils.notEmpty(view.getPost_text())) entity.setPostText(view.getPost_text());
        else view.setPost_text(entity.getPostText());
        if (view.getCategory()!=null) entity.setCategory(service.getById(view.getCategory()));
        else if (entity.getCategory()!=null) view.setCategory(entity.getCategory().getId());

    }
}
