package com.cwbusinesservices.services.service;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.pojo.entities.ServiceEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
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
public class ServiceValidationServiceImpl extends BaseValidator<ServiceEntity,Integer> implements IServiceValidationService{

    public ServiceValidationServiceImpl(){
        super(PermissionsEnum.CREATE_SERVICE,PermissionsEnum.EDIT_SERVICE,PermissionsEnum.DELETE_SERVICE,ServiceEntity.class);
    }

    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private Validator validator;

    @Override
    public void validForView(ServiceEntity entity) throws BaseException {
        if (entity.isActive())
            return;
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_SERVICE))
            throw new ForbiddenException();
    }
}
