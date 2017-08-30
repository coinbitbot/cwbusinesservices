package com.cwbusinesservices.services.employees;

import com.cwbusinesservices.convertors.EmployeeConverter;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.EmployeeCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 29.08.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class EmployeeServiceImpl extends IEmployeeService{

    @Autowired
    private EmployeeConverter converter;

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<EmployeeEntity> criteria = new EmployeeCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<EmployeeEntity> criteria = new EmployeeCriteria(restrict);
        return count(criteria);
    }
}
