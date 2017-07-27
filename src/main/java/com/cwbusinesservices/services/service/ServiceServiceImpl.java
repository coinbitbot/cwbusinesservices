package com.cwbusinesservices.services.service;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.ServiceCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.ServiceEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 27.07.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class ServiceServiceImpl extends IServiceService{
    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<ServiceEntity> criteria = new ServiceCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<ServiceEntity> criteria = new ServiceCriteria(restrict);
        return count(criteria);
    }
}
