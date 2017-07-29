package com.cwbusinesservices.services;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.BlockEntity;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 29.07.2017.
 */
public abstract class BaseValidator<E extends GetableById<I>, I> implements IValidator<E>{
    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private Validator validator;

    private PermissionsEnum permissionCreate;
    private PermissionsEnum permissionEdit;
    private PermissionsEnum permissionDelete;
    private Class<E> persistentClass;

    public BaseValidator(PermissionsEnum permissionCreate,PermissionsEnum permissionEdit,PermissionsEnum permissionDelete, Class<E> persistentClass){
        this.permissionCreate = permissionCreate;
        this.permissionDelete = permissionDelete;
        this.permissionEdit =  permissionEdit;
        this.persistentClass = persistentClass;
    }


    @Override
    public void validForCreate(E entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(permissionCreate))
            throw new ForbiddenException();
        GetableById<I> id = entity;
        if (id.compareId(0)>0)
            throw new EntityValidateException("errors.EntityCreateException.id.create");
        Set<ConstraintViolation<E>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(persistentClass.getName(), violations);
        }
    }

    @Override
    public void validForUpdate(E entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(permissionEdit))
            throw new ForbiddenException();
        GetableById<I> id = entity;
        if (id.compareId(0)==0)
            throw new EntityValidateException("errors.EntityCreateException.id.update");
        Set<ConstraintViolation<E>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(persistentClass.getName(), violations);
        }
    }

    @Override
    public void validForView(E entity) throws BaseException {
        return;
    }

    @Override
    public void validForView(List<E> entities) throws BaseException {
        for (E entity:entities)
            validForView(entity);
    }

    @Override
    public void validForDelete(E entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(permissionDelete))
            throw new ForbiddenException();
    }

}
