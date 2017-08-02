package com.cwbusinesservices.services.blog;

import com.cwbusinesservices.convertors.BlogCategoryConverter;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.BlogCategoryCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.persistence.dao.repositories.BlogCategoryRepository;
import com.cwbusinesservices.pojo.entities.BlogCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 02.08.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class BlogCategoryServiceImpl extends IBlogCategoryService{

    @Autowired
    private BlogCategoryRepository repository;
    @Autowired
    private BlogCategoryConverter converter;

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<BlogCategoryEntity> criteria = new BlogCategoryCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<BlogCategoryEntity> criteria = new BlogCategoryCriteria(restrict);
        return count(criteria);
    }

    @Override
    public Map<String, Object> getByCode(String code, Set<String> fields) throws BaseException {
        return converter.convert(getByCode(code),fields);
    }

    @Override
    public BlogCategoryEntity getByCode(String code) throws BaseException {
        BlogCategoryEntity entity = repository.findOneByCode(code);
        if(entity==null)
            throw new NoSuchEntityException(BlogCategoryEntity.class.getName(),"code = "+code);
        return entity;
    }
}
