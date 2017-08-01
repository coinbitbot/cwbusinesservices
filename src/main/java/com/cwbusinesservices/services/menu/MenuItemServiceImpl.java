package com.cwbusinesservices.services.menu;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.MenuItemCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.MenuItemEntity;
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
public class MenuItemServiceImpl extends IMenuItemService {

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<MenuItemEntity> criteria = new MenuItemCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<MenuItemEntity> criteria = new MenuItemCriteria(restrict);
        return count(criteria);
    }
}
