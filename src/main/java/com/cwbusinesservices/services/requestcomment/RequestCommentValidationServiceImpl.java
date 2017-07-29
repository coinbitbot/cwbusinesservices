package com.cwbusinesservices.services.requestcomment;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.pojo.entities.RequestCommentEntity;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 29.07.2017.
 */
@Service
public class RequestCommentValidationServiceImpl extends BaseValidator<RequestCommentEntity,Integer> implements IRequestCommentValidationService{
    public RequestCommentValidationServiceImpl(){
        super(PermissionsEnum.CREATE_REQUEST_COMMENT,PermissionsEnum.EDIT_REQUEST_COMMENT,PermissionsEnum.DELETE_REQUEST_COMMENT, RequestCommentEntity.class);
    }

    @Autowired
    private SessionUtils sessionUtils;

    @Override
    public void validForUpdate(RequestCommentEntity entity) throws BaseException {
        super.validForUpdate(entity);
        if (!sessionUtils.isOwner(entity))
            throw new ForbiddenException();
    }

    @Override
    public void validForView(RequestCommentEntity entity) throws BaseException {
        super.validForView(entity);
        if(!sessionUtils.isOwner(entity)&&!sessionUtils.isUserWithPermission(PermissionsEnum.VIEW_REQUEST_COMMENT))
            throw new ForbiddenException();
    }

    @Override
    public void validForDelete(RequestCommentEntity entity) throws BaseException {
        if (!sessionUtils.isOwner(entity)||!sessionUtils.isUserWithPermission(PermissionsEnum.DELETE_REQUEST_COMMENT))
            throw new ForbiddenException();
    }
}
