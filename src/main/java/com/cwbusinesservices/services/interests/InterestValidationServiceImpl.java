package com.cwbusinesservices.services.interests;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.pojo.entities.InterestEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 29.07.2017.
 */
@Service
public class InterestValidationServiceImpl extends BaseValidator<InterestEntity,Integer> implements IInterestValidationService{
    public InterestValidationServiceImpl(){
        super(PermissionsEnum.CREATE_INTEREST,PermissionsEnum.EDIT_INTEREST,PermissionsEnum.DELETE_INTEREST, InterestEntity.class);
    }

    @Override
    public void validForDelete(InterestEntity entity) throws BaseException {
        super.validForDelete(entity);
        //TODO CHECK REQUESTS IF ANY HAVE INTEREST
    }
}
