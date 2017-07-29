package com.cwbusinesservices.services.industry;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.IndustryEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 29.07.2017.
 */
@Service
public class IndustryValidationServiceImpl extends BaseValidator<IndustryEntity, Integer> implements IIndustryValidationService {
    public IndustryValidationServiceImpl(){
        super(PermissionsEnum.CREATE_INDUSTRY,PermissionsEnum.EDIT_INDUSTRY,PermissionsEnum.DELETE_INDUSTRY,IndustryEntity.class);
    }

    @Override
    public void validForDelete(IndustryEntity entity) throws BaseException {
        super.validForDelete(entity);
        //TODO CHECK REQUESTS IF ANY HAVE INDUSTRY
    }
}
