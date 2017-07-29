package com.cwbusinesservices.services.role;

import com.cwbusinesservices.criteria.impl.RoleCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.service_error.ActionNotAllowedException;
import com.cwbusinesservices.pojo.view.RoleView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oleh on 29.07.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class RoleServiceImpl extends IRoleService {

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        RoleCriteria criteria = new RoleCriteria(restrict);
        return getList(criteria, fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        RoleCriteria criteria = new RoleCriteria(restrict);
        return count(criteria);
    }

    @Override
    public boolean delete(Integer id) throws BaseException {
        throw new ActionNotAllowedException("error.role.delete.not.allowed");
    }

    @Override
    public Integer create(RoleView view) throws BaseException, IllegalAccessException, InstantiationException {
        throw new ActionNotAllowedException("error.role.create.not.allowed");
    }

    @Override
    public boolean update(RoleView view) throws BaseException {
        throw new ActionNotAllowedException("error.role.edit.not.allowed");
    }


}
