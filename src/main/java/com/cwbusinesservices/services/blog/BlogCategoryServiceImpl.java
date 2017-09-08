package com.cwbusinesservices.services.blog;

import com.cwbusinesservices.convertors.BlogCategoryConverter;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.BlogCategoryCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.mergers.BlogCategoryMerger;
import com.cwbusinesservices.persistence.dao.repositories.BlogCategoryRepository;
import com.cwbusinesservices.pojo.entities.BlogCategoryEntity;
import com.cwbusinesservices.pojo.enums.OrderDirectionEnum;
import com.cwbusinesservices.pojo.view.BlogCategoryView;
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
@Transactional(propagation= Propagation.REQUIRED, rollbackFor = BaseException.class)
public class BlogCategoryServiceImpl extends IBlogCategoryService{

    @Autowired
    private BlogCategoryRepository repository;
    @Autowired
    private BlogCategoryConverter converter;
    @Autowired
    private BlogCategoryMerger merger;
    @Autowired
    private BlogCategoryValidationServiceImpl blogCategoryValidationService;

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
    @Transactional(propagation = Propagation.NEVER)
    public Integer create(BlogCategoryView view) throws BaseException, IllegalAccessException, InstantiationException {
        try {
            BlogCategoryCriteria criteria = new BlogCategoryCriteria();
            criteria.setOrder_by("position");
            criteria.setOrder_direction(OrderDirectionEnum.DESC);
            criteria.setLimit(1);

            List<BlogCategoryEntity> list = getList(criteria);

            view.setPosition(list.get(0).getPosition() + 1);
        } catch (Exception e) {
            view.setPosition(1);
        }

        return super.create(view);
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

    @Override
    public boolean swap(BlogCategoryCriteria criteria) throws BaseException {
        criteria.setLimit(2);

        List<BlogCategoryEntity> list = getList(criteria);
        if (list.size() != 2) {
            throw new ServiceErrorException();
        }

        BlogCategoryEntity a = list.get(0);
        BlogCategoryEntity b = list.get(1);

        int temp = a.getPosition();
        a.setPosition(b.getPosition());
        b.setPosition(temp);

        return true;
    }
}
