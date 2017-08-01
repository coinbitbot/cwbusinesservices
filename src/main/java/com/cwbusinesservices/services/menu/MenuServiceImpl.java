package com.cwbusinesservices.services.menu;

import com.cwbusinesservices.convertors.MenuConverter;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.MenuCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.persistence.dao.repositories.MenuRepository;
import com.cwbusinesservices.pojo.entities.MenuEntity;
import com.cwbusinesservices.pojo.enums.MenuCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 01.08.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class MenuServiceImpl extends IMenuService{

    @Autowired
    private MenuConverter converter;
    @Autowired
    private MenuRepository repository;

    @Override
    public Map<String, Object> getByCode(MenuCodeEnum code, Set<String> fields) throws BaseException {
        MenuEntity entity = repository.findByCode(code);
        if (entity==null)
            throw new NoSuchEntityException(MenuEntity.class.getName(),"code "+code);
        return converter.convert(entity,fields);
    }

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<MenuEntity> criteria = new MenuCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<MenuEntity> criteria = new MenuCriteria(restrict);
        return count(criteria);
    }
}
