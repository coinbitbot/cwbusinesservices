package com.cwbusinesservices.services.request;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.pojo.enums.RequestStatusEnum;
import com.cwbusinesservices.pojo.view.RequestView;
import com.cwbusinesservices.services.BaseValidator;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Collections;

/**
 * Created by Andrii on 29.07.2017.
 */
@Service
public class RequestValidationServiceImpl extends BaseValidator<RequestEntity,Integer> implements IRequestValidationService{
    public RequestValidationServiceImpl(){
        super(PermissionsEnum.CREATE_REQUEST,PermissionsEnum.EDIT_REQUEST,PermissionsEnum.DELETE_REQUEST,RequestEntity.class);
    }

    @Autowired
    private SessionUtils sessionUtils;

    @Override
    public void validForCreate(RequestEntity entity) throws BaseException {
        super.validForCreate(entity);

        if ((entity.getInterests() == null || entity.getInterests().isEmpty()) &&
                StringUtils.isBlank(entity.getInterestAlter())) {
            throw new ValidationException("request", "request.interests.required");
        }
    }

    @Override
    public void validForUpdate(RequestEntity entity) throws BaseException {
        super.validForUpdate(entity);

        if ((entity.getInterests() == null || entity.getInterests().isEmpty()) &&
                StringUtils.isBlank(entity.getInterestAlter())) {
            throw new ValidationException("request", "request.interests.required");
        }

        if (!sessionUtils.isOwner(entity))
            throw new ForbiddenException();
    }

    @Override
    public void validForView(RequestEntity entity) throws BaseException {
        super.validForView(entity);
        if(!sessionUtils.isOwner(entity)&&!sessionUtils.isUserWithPermission(PermissionsEnum.VIEW_REQUEST))
            throw new ForbiddenException();
    }

    @Override
    public void validForDelete(RequestEntity entity) throws BaseException {
        super.validForDelete(entity);
        if (!sessionUtils.isOwner(entity))
            throw new ForbiddenException();
    }

    @Override
    public void validForStatusChange(RequestView view, RequestEntity entity)  throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.STATUS_CHANGE_REQUEST))
            throw new ForbiddenException();
        if (view.getStatus().ordinal()<entity.getStatus().ordinal())
            throw new EntityValidateException("request.error.status.change");
    }
}
