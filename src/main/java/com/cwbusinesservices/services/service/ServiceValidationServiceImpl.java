package com.cwbusinesservices.services.service;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.pojo.entities.ServiceEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 27.07.2017.
 */
@Service
public class ServiceValidationServiceImpl implements IServiceValidationService{

    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private Validator validator;

    @Override
    public void validForCreate(ServiceEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.CREATE_SERVICE))
            throw new ForbiddenException();
        if (entity.getId()>0)
            throw new EntityValidateException("errors.EntityCreateException.id.create");
        Set<ConstraintViolation<ServiceEntity>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(ServiceEntity.class.getName(), violations);
        }
    }

    @Override
    public void validForUpdate(ServiceEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_SERVICE))
            throw new ForbiddenException();
        if (entity.getId()==0)
            throw new EntityValidateException("errors.EntityCreateException.id.update");
        Set<ConstraintViolation<ServiceEntity>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(ServiceEntity.class.getName(), violations);
        }
    }

    @Override
    public void validForView(ServiceEntity entity) throws BaseException {
        if (entity.isActive())
            return;
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_SERVICE))
            throw new ForbiddenException();
    }

    @Override
    public void validForView(List<ServiceEntity> entities) throws BaseException {
        for (ServiceEntity entity:entities)
            validForView(entity);
    }

    @Override
    public void validForDelete(ServiceEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.DELETE_SERVICE))
            throw new ForbiddenException();
    }
}
