package com.cwbusinesservices.services.blog;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.CompanyCriteria;
import com.cwbusinesservices.criteria.impl.PostCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.pojo.entities.PostEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 02.08.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class PostServiceImpl extends IPostService{
    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<PostEntity> criteria = new PostCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<PostEntity> criteria = new PostCriteria(restrict);
        return count(criteria);
    }
}
