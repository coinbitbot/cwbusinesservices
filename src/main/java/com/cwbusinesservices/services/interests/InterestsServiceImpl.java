package com.cwbusinesservices.services.interests;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.InterestCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.mergers.InterestMerger;
import com.cwbusinesservices.persistence.dao.repositories.InterestRepository;
import com.cwbusinesservices.pojo.entities.InterestEntity;
import com.cwbusinesservices.pojo.enums.OrderDirectionEnum;
import com.cwbusinesservices.pojo.view.InterestView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 29.07.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED, rollbackFor = BaseException.class)
public class InterestsServiceImpl extends IInterestsService{

    @Autowired
    private InterestRepository repository;

    @Autowired
    private InterestMerger merger;

    @Autowired
    private IInterestValidationService interestValidationService;

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<InterestEntity> criteria = new InterestCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<InterestEntity> criteria = new InterestCriteria(restrict);
        return count(criteria);
    }

    @Override
    public Integer create(InterestView view) throws BaseException, IllegalAccessException, InstantiationException {
        InterestEntity entity = new InterestEntity();
        merger.merge(entity,view);

        try {
            InterestCriteria criteria = new InterestCriteria();
            criteria.setOrder_by("position");
            criteria.setOrder_direction(OrderDirectionEnum.DESC);
            criteria.setLimit(1);

            List<InterestEntity> list = getList(criteria);

            entity.setPosition(list.get(0).getPosition() + 1);
        } catch (Exception e) {
            entity.setPosition(1);
        }

        interestValidationService.validForCreate(entity);
        entity = repository.saveAndFlush(entity);
        if(entity==null)
            throw new ServiceErrorException();
        return entity.getId();
    }

    @Override
    public boolean swap(InterestCriteria criteria) throws BaseException {
        criteria.setLimit(2);

        List<InterestEntity> list = getList(criteria);
        if (list.size() != 2) {
            throw new ServiceErrorException();
        }

        InterestEntity a = list.get(0);
        InterestEntity b = list.get(1);

        int temp = a.getPosition();
        a.setPosition(b.getPosition());
        b.setPosition(temp);

        return true;
    }
}
