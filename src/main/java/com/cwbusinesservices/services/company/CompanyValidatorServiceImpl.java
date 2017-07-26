package com.cwbusinesservices.services.company;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.CompanyEntity;
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
 * Created by Andrii on 26.07.2017.
 */
@Service
public class CompanyValidatorServiceImpl implements ICompanyValidationService{

    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private Validator validator;

    @Override
    public void validForCreate(CompanyEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.CREATE_COMPANY))
            throw new ForbiddenException();
        if (entity.getId()>0)
            throw new EntityValidateException("errors.EntityCreateException.id.create");
        Set<ConstraintViolation<CompanyEntity>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(CompanyEntity.class.getName(), violations);
        }
    }

    @Override
    public void validForUpdate(CompanyEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_COMPANY))
            throw new ForbiddenException();
        if (entity.getId()==0)
            throw new EntityValidateException("errors.EntityCreateException.id.update");
        Set<ConstraintViolation<CompanyEntity>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(CompanyEntity.class.getName(), violations);
        }
    }

    @Override
    public void validForView(CompanyEntity entity) throws BaseException {
        if (entity.isActive())
            return;
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_COMPANY))
            throw new ForbiddenException();
    }

    @Override
    public void validForView(List<CompanyEntity> entities) throws BaseException {
        for (CompanyEntity entity:entities)
            validForView(entity);
    }

    @Override
    public void validForDelete(CompanyEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.DELETE_COMPANY))
            throw new ForbiddenException();
    }
}
