package com.cwbusinesservices.services.service;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.ServiceCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.mergers.ServiceMerger;
import com.cwbusinesservices.persistence.dao.repositories.ServiceRepository;
import com.cwbusinesservices.pojo.entities.ServiceEntity;
import com.cwbusinesservices.pojo.enums.OrderDirectionEnum;
import com.cwbusinesservices.pojo.view.ServiceView;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional(propagation= Propagation.REQUIRED, rollbackFor = BaseException.class)
public class ServiceServiceImpl extends IServiceService{

    @Autowired
    private ServiceRepository repository;

    @Autowired
    private ServiceMerger merger;

    @Autowired
    private IServiceValidationService serviceValidationService;

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

    @Override
    public Integer create(ServiceView view) throws BaseException, IllegalAccessException, InstantiationException {
        ServiceEntity entity = new ServiceEntity();
        merger.merge(entity,view);

        try {
            ServiceCriteria criteria = new ServiceCriteria();
            criteria.setOrder_by("position");
            criteria.setOrder_direction(OrderDirectionEnum.DESC);
            criteria.setLimit(1);

            List<ServiceEntity> list = getList(criteria);

            entity.setPosition(list.get(0).getPosition() + 1);
        } catch (Exception e) {
            entity.setPosition(1);
        }

        serviceValidationService.validForCreate(entity);
        entity = repository.saveAndFlush(entity);
        if(entity == null)
            throw new ServiceErrorException();
        return entity.getId();
    }

    @Override
    public boolean swap(ServiceCriteria criteria) throws BaseException {
        criteria.setLimit(2);

        List<ServiceEntity> list = getList(criteria);
        if (list.size() != 2) {
            throw new ServiceErrorException();
        }

        ServiceEntity a = list.get(0);
        ServiceEntity b = list.get(1);

        int temp = a.getPosition();
        a.setPosition(b.getPosition());
        b.setPosition(temp);

        return true;
    }
}
