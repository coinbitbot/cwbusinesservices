package com.cwbusinesservices.services.templates;

import com.cwbusinesservices.convertors.Converter;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.EmailTemplateCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.persistence.dao.repositories.EmailTemplateRepository;
import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
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
@Transactional(propagation= Propagation.REQUIRED, rollbackFor = BaseException.class)
public class EmailTemplateServiceImpl extends IEmailTemplateService{

    @Autowired
    private EmailTemplateRepository repository;
    @Autowired
    private IValidator<EmailTemplateEntity> validator;
    @Autowired
    private Converter<EmailTemplateEntity> converter;


    @Override
    public EmailTemplateEntity getByCode(EmailTemplateCodeEnum code) throws BaseException {
        EmailTemplateEntity entity = repository.findByCode(code);
        if (entity == null)
            throw new NoSuchEntityException(EmailTemplateEntity.class.getName(), "code " + code);
        validator.validForView(entity);
        return entity;
    }

    @Override
    public Map<String, Object> getByCode(EmailTemplateCodeEnum code, Set<String> fields) throws BaseException {
        return converter.convert(getByCode(code),fields);
    }

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<EmailTemplateEntity> criteria = new EmailTemplateCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<EmailTemplateEntity> criteria = new EmailTemplateCriteria(restrict);
        return count(criteria);
    }
}
