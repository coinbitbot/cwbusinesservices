package com.cwbusinesservices.services.blocks;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.service_error.ActionNotAllowedException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.BlockEntity;
import com.cwbusinesservices.pojo.entities.CompanyEntity;
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
 * Created by Andrii on 28.07.2017.
 */
@Service
public class BlockValidationService extends BaseValidator<BlockEntity, Integer> implements IBlockValidationService{

    public BlockValidationService(){
        super(PermissionsEnum.CREATE_BLOCK,PermissionsEnum.EDIT_BLOCK,PermissionsEnum.DELETE_BLOCK, BlockEntity.class);
    }

    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private Validator validator;

    @Override
    public void validForCreate(BlockEntity entity) throws BaseException {
        throw new ActionNotAllowedException("block.error.create");
    }

    @Override
    public void validForDelete(BlockEntity entity) throws BaseException {
        throw new ActionNotAllowedException("block.error.delete");
    }
}
