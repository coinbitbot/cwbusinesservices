package com.cwbusinesservices.services.industry;

import com.cwbusinesservices.convertors.Fields;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.RequestCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.service_error.ActionNotAllowedException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.pojo.entities.IndustryEntity;
import com.cwbusinesservices.pojo.entities.RequestEntity;
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
public class IndustryValidationServiceImpl extends BaseValidator<IndustryEntity, Integer> implements IIndustryValidationService {

    @Autowired
    private IRequestService requestService;

    public IndustryValidationServiceImpl(){
        super(PermissionsEnum.CREATE_INDUSTRY,PermissionsEnum.EDIT_INDUSTRY,PermissionsEnum.DELETE_INDUSTRY,IndustryEntity.class);
    }

    @Override
    public void validForDelete(IndustryEntity entity) throws BaseException {
        super.validForDelete(entity);
        RequestCriteria criteria = new RequestCriteria();
        List<Integer> ids = new ArrayList<>();
        ids.add(entity.getId());
        criteria.setIndustry_ids(ids);
        Integer requestEntities = requestService.count(criteria);
        if (! requestEntities.equals(0))
            throw new ActionNotAllowedException("error.delete.industry.has.requests");
    }
}
