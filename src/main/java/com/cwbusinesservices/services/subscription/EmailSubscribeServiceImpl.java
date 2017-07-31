package com.cwbusinesservices.services.subscription;

import com.cwbusinesservices.convertors.Converter;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.EmailSubscribeCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.persistence.dao.repositories.EmailSubscribeRepository;
import com.cwbusinesservices.pojo.entities.EmailSubscribeEntity;
import com.cwbusinesservices.pojo.entities.InfoPageEntity;
import com.cwbusinesservices.services.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 31.07.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class EmailSubscribeServiceImpl extends IEmailSubscribeService{

    @Autowired
    private EmailSubscribeRepository repository;
    @Autowired
    private IValidator<EmailSubscribeEntity> validator;
    @Autowired
    private Converter<EmailSubscribeEntity> converter;

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<EmailSubscribeEntity> criteria = new EmailSubscribeCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<EmailSubscribeEntity> criteria = new EmailSubscribeCriteria(restrict);
        return count(criteria);
    }

    @Override
    public EmailSubscribeEntity getByEmail(String email) throws BaseException {
        EmailSubscribeEntity entity = repository.findByEmail(email);
        if (entity == null)
            throw new NoSuchEntityException(InfoPageEntity.class.getName(), "email " + email);
        validator.validForView(entity);
        return entity;
    }

    @Override
    public Map<String, Object> getByEmail(String email, Set<String> fields) throws BaseException {
        return converter.convert(getByEmail(email),fields);
    }
}
