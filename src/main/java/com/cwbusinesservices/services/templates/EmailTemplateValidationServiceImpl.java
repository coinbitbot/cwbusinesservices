package com.cwbusinesservices.services.templates;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 31.07.2017.
 */
@Service
public class EmailTemplateValidationServiceImpl extends BaseValidator<EmailTemplateEntity,Integer> implements IEmailTemplateValidationService{

    @Autowired
    private IEmailTemplateService service;

    public EmailTemplateValidationServiceImpl(){
        super(PermissionsEnum.CREATE_EMAIL_TEMPLATE,PermissionsEnum.EDIT_EMAIL_TEMPLATE,PermissionsEnum.DELETE_EMAIL_TEMPLATE,EmailTemplateEntity.class);
    }

    @Override
    public void validForCreate(EmailTemplateEntity entity) throws BaseException {
        super.validForCreate(entity);
        try{
            service.getByCode(entity.getCode());
            throw new EntityValidateException("email.template.code.is.not.uniq");
        }catch (NoSuchEntityException ex){
        }
    }

    @Override
    public void validForUpdate(EmailTemplateEntity entity) throws BaseException {
        super.validForUpdate(entity);
        try{
            EmailTemplateEntity dbEntity = service.getByCode(entity.getCode());
            if (dbEntity.getId()!=entity.getId())
                throw new EntityValidateException("email.template.code.is.not.uniq");
        }catch (NoSuchEntityException ex){
        }
    }

}
