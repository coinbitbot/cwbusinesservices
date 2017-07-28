package com.cwbusinesservices.services.blocks;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.BlockEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 28.07.2017.
 */
@Service
public class BlockValidationService implements IBlockValidationService{

    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private Validator validator;

    @Override
    public void validForCreate(BlockEntity entity) throws BaseException {
        throw new UnsupportedOperationException("block.error.create");
    }

    @Override
    public void validForUpdate(BlockEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_BLOCK))
            throw new ForbiddenException();
        if (entity.getId()==0)
            throw new EntityValidateException("errors.EntityCreateException.id.update");
        Set<ConstraintViolation<BlockEntity>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(BlockEntity.class.getName(), violations);
        }
    }

    @Override
    public void validForView(BlockEntity entity) throws BaseException {

    }

    @Override
    public void validForView(List<BlockEntity> entities) throws BaseException {

    }

    @Override
    public void validForDelete(BlockEntity entity) throws BaseException {
        throw new UnsupportedOperationException("block.error.delete");
    }
}
