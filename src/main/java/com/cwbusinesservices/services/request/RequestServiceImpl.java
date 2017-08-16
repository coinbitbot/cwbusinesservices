package com.cwbusinesservices.services.request;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.CompanyCriteria;
import com.cwbusinesservices.criteria.impl.RequestCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.persistence.dao.repositories.RequestRepository;
import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import com.cwbusinesservices.pojo.enums.RequestStatusEnum;
import com.cwbusinesservices.pojo.view.RequestView;
import com.cwbusinesservices.services.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by Andrii on 29.07.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED, rollbackFor = BaseException.class)
public class RequestServiceImpl extends IRequestService{

    @Autowired
    private IRequestValidationService validationService;
    @Autowired
    private RequestRepository repository;

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<RequestEntity> criteria = new RequestCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<RequestEntity> criteria = new RequestCriteria(restrict);
        return count(criteria);
    }

    @Override
    public boolean delete(Integer id) throws BaseException {
        RequestEntity entity = getById(id);
        if (entity == null)
            throw new NoSuchEntityException(RequestEntity.class.getName(), "id" + id);
        validationService.validForDelete(entity);
        entity.setStatus(RequestStatusEnum.DELETED);
        entity = repository.saveAndFlush(entity);
        return entity!=null;
    }

    @Override
    public Boolean changeStatus(RequestView view) throws BaseException {
        RequestEntity entity = getById(view.getId());
        if (entity == null)
            throw new NoSuchEntityException(RequestEntity.class.getName(), "id" + id);
        validationService.validForStatusChange(view,entity);
        entity.setStatus(view.getStatus());
        entity = repository.saveAndFlush(entity);
        return entity!=null;
    }
}
