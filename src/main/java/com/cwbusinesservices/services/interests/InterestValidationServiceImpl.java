package com.cwbusinesservices.services.interests;

import com.cwbusinesservices.criteria.impl.RequestCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.pojo.entities.InterestEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import com.cwbusinesservices.services.request.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 29.07.2017.
 */
@Service
public class InterestValidationServiceImpl extends BaseValidator<InterestEntity,Integer> implements IInterestValidationService{

    @Autowired
    private IRequestService requestService;

    public InterestValidationServiceImpl(){
        super(PermissionsEnum.CREATE_INTEREST,PermissionsEnum.EDIT_INTEREST,PermissionsEnum.DELETE_INTEREST, InterestEntity.class);
    }

    @Override
    public void validForDelete(InterestEntity entity) throws BaseException {
        super.validForDelete(entity);
        RequestCriteria criteria = new RequestCriteria();
        List<Integer> ids = new ArrayList<>();
        ids.add(entity.getId());
        criteria.setInterest_ids(ids);
        Integer requestEntities =requestService.count(criteria);
        if (requestEntities!=0)
            throw new ForbiddenException();
    }
}
