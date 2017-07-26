package com.cwbusinesservices.services.company;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.CompanyCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.CompanyEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 26.07.2017.
 */
//@Service
public class CompanyServiceImpl extends ICompanyService{

    @Override
    public List<Map<String, Object>> getList(int offset, int limit, Set<String> fields, String restrict) throws BaseException {
        Criteria<CompanyEntity> criteria = new CompanyCriteria(offset,limit,restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<CompanyEntity> criteria = new CompanyCriteria(restrict);
        return count(criteria);
    }
}