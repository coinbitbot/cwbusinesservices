package com.cwbusinesservices.services.infopages;

import com.cwbusinesservices.criteria.impl.InfoPageCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.InfoPageEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.utils.SessionUtils;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 25.07.2017.
 */
@Service
public class InfoPageValidationServiceImpl implements IInfoPageValidationService {

    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private Validator validator;
    @Autowired
    private IInfoPageService service;
    @Autowired
    private Utils utils;

    @Override
    public void validForCreate(InfoPageEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.CREATE_INFO_PAGE))
            throw new ForbiddenException();
        if (entity.getId()>0)
            throw new EntityValidateException("errors.EntityCreateException.id.create");
        Set<ConstraintViolation<InfoPageEntity>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(InfoPageEntity.class.getName(), violations);
        }
        if (!utils.validUrl(entity.getUrl()))
            throw new EntityValidateException("errors.InfoPage.url.is.not.valid");
        try{
            service.getByUrl(entity.getUrl());
            throw new EntityValidateException("errors.InfoPage.url.is.not.uniq");
        }catch (NoSuchEntityException ex){
        }
    }

    @Override
    public void validForUpdate(InfoPageEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_INFO_PAGE))
            throw new ForbiddenException();
        if (entity.getId()==0)
            throw new EntityValidateException("errors.EntityCreateException.id.update");
        Set<ConstraintViolation<InfoPageEntity>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(InfoPageEntity.class.getName(), violations);
        }
        if (!utils.validUrl(entity.getUrl()))
            throw new EntityValidateException("errors.InfoPage.url.is.not.valid");
        try{
            InfoPageEntity dbEntity = service.getByUrl(entity.getUrl());
            if (dbEntity.getId()!=entity.getId())
                throw new EntityValidateException("errors.InfoPage.url.is.not.uniq");
        }catch (NoSuchEntityException ex){
        }
    }

    @Override
    public void validForView(InfoPageEntity entity) throws ForbiddenException {
        if (entity.isActive())
            return;
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_INFO_PAGE))
            throw new ForbiddenException();
    }

    @Override
    public void validForView(List<InfoPageEntity> entities) throws ForbiddenException {
        for (InfoPageEntity entity:entities)
            validForView(entity);
    }

    @Override
    public void validForDelete(InfoPageEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.DELETE_INFO_PAGE))
            throw new ForbiddenException();
    }


}
