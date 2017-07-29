package com.cwbusinesservices.services.role;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.service_error.ActionNotAllowedException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.pojo.entities.RoleEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Oleh on 29.07.2017.
 */
@Service
public class RoleValidationServiceImpl
        extends BaseValidator<RoleEntity, Integer>
        implements IRoleValidationService {

    public RoleValidationServiceImpl() {
        super(PermissionsEnum.CREATE_BLOCK, PermissionsEnum.EDIT_BLOCK,
                PermissionsEnum.DELETE_BLOCK, RoleEntity.class);
    }

    @Autowired
    private SessionUtils sessionUtils;

    @Override
    public void validForCreate(RoleEntity entity) throws BaseException {
        throw new ActionNotAllowedException("error.role.create.not.allowed");
    }

    @Override
    public void validForUpdate(RoleEntity entity) throws BaseException {
        throw new ActionNotAllowedException("error.role.edit.not.allowed");
    }

    @Override
    public void validForView(RoleEntity entity) throws BaseException {
        if (!sessionUtils.isAdmin())
            throw new ForbiddenException();
    }

    @Override
    public void validForDelete(RoleEntity entity) throws BaseException {
        throw new ActionNotAllowedException("error.role.delete.not.allowed");
    }
}
