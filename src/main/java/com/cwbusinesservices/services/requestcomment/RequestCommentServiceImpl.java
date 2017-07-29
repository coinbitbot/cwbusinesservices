package com.cwbusinesservices.services.requestcomment;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.CompanyCriteria;
import com.cwbusinesservices.criteria.impl.RequestCommentCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.pojo.entities.RequestCommentEntity;
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
@Transactional(propagation= Propagation.REQUIRED)
public class RequestCommentServiceImpl extends IRequestCommentService{
    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<RequestCommentEntity> criteria = new RequestCommentCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<RequestCommentEntity> criteria = new RequestCommentCriteria(restrict);
        return count(criteria);
    }
}
