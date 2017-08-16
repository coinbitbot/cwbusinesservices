package com.cwbusinesservices.services.testimonial;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.TestimonialCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.TestimonialEntity;
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
public class TestimonialServiceImpl extends ITestimonialService{
    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<TestimonialEntity> criteria = new TestimonialCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<TestimonialEntity> criteria = new TestimonialCriteria(restrict);
        return count(criteria);
    }
}
