package com.cwbusinesservices.services.interests;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.IndustryCriteria;
import com.cwbusinesservices.criteria.impl.InterestCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.IndustryEntity;
import com.cwbusinesservices.pojo.entities.InterestEntity;
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
}
