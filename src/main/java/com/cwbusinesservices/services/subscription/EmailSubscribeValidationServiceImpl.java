package com.cwbusinesservices.services.subscription;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.entities.EmailSubscribeEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 31.07.2017.
 */
@Service
public class EmailSubscribeValidationServiceImpl extends BaseValidator<EmailSubscribeEntity,Integer> implements IEmailSubscribeValidationService{

    @Autowired
    private IEmailSubscribeService service;

    public EmailSubscribeValidationServiceImpl(){
        super(PermissionsEnum.ANYONE,PermissionsEnum.EDIT_EMAIL_SUBSCRIBE,PermissionsEnum.DELETE_EMAIL_SUBSCRIBE,EmailSubscribeEntity.class);
    }

    @Override
    public void validForCreate(EmailSubscribeEntity entity) throws BaseException {
        super.validForCreate(entity);
        try{
            service.getByEmail(entity.getEmail());
            throw new EntityValidateException("email.subscribe.email.is.not.uniq");
        }catch (NoSuchEntityException ex){
        }
    }

    @Override
    public void validForUpdate(EmailSubscribeEntity entity) throws BaseException {
        super.validForUpdate(entity);
        try{
            EmailSubscribeEntity dbEntity = service.getByEmail(entity.getEmail());
            if (dbEntity.getId()!=entity.getId())
                throw new EntityValidateException("email.subscribe.email.is.not.uniq");
        }catch (NoSuchEntityException ex){
        }
    }

}
