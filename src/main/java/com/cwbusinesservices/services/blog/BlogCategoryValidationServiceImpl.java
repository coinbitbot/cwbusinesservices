package com.cwbusinesservices.services.blog;

import com.cwbusinesservices.criteria.impl.PostCriteria;
import com.cwbusinesservices.criteria.impl.RequestCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.persistence.dao.repositories.BlogCategoryRepository;
import com.cwbusinesservices.pojo.entities.BlogCategoryEntity;
import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import com.cwbusinesservices.pojo.entities.IndustryEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 02.08.2017.
 */
@Service
public class BlogCategoryValidationServiceImpl extends BaseValidator<BlogCategoryEntity,Integer> implements IBlogCategoryValidationService{

    @Autowired
    private IPostService postService;
    @Autowired
    private IBlogCategoryService service;

    public BlogCategoryValidationServiceImpl(){
        super(PermissionsEnum.CREATE_BLOG_CATEGORY,PermissionsEnum.EDIT_BLOG_CATEGORY,PermissionsEnum.DELETE_BLOG_CATEGORY,BlogCategoryEntity.class);
    }

    @Override
    public void validForCreate(BlogCategoryEntity entity) throws BaseException {
        super.validForCreate(entity);
        try{
            service.getByCode(entity.getCode());
            throw new EntityValidateException("blog.category.code.is.not.uniq");
        }catch (NoSuchEntityException ex){
        }
    }

    @Override
    public void validForUpdate(BlogCategoryEntity entity) throws BaseException {
        super.validForUpdate(entity);
        try{
            BlogCategoryEntity dbEntity = service.getByCode(entity.getCode());
            if (dbEntity.getId()!=entity.getId())
                throw new EntityValidateException("blog.category.code.is.not.uniq");
        }catch (NoSuchEntityException ex){
        }
    }

    @Override
    public void validForDelete(BlogCategoryEntity entity) throws BaseException {
        super.validForDelete(entity);
        PostCriteria criteria = new PostCriteria();
        criteria.setCategory(entity.getId());
        Integer requestEntities =postService.count(criteria);
        if (requestEntities!=0)
            throw new ForbiddenException();
    }

}
