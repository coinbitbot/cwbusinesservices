package com.cwbusinesservices.services.menu;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.pojo.entities.MenuItemEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import com.cwbusinesservices.services.utils.SessionUtils;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Validator;

/**
 * Created by Andrii on 01.08.2017.
 */
@Service
public class MenuItemValidationServiceImpl extends BaseValidator<MenuItemEntity,Integer> implements IMenuItemValidationService{

    public MenuItemValidationServiceImpl(){
        super(PermissionsEnum.CREATE_MENU_ITEM,PermissionsEnum.EDIT_MENU_ITEM,PermissionsEnum.DELETE_MENU_ITEM, MenuItemEntity.class);
    }

    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private Validator validator;
    @Autowired
    private Utils utils;

    @Override
    public void validForCreate(MenuItemEntity entity) throws BaseException {
        super.validForCreate(entity);
        if (entity.getMenu()==null&&entity.getParentMenuItem()==null)
            throw new EntityValidateException("menu.item.father.error");
    }

    @Override
    public void validForUpdate(MenuItemEntity entity) throws BaseException {
        super.validForUpdate(entity);
        if (entity.getMenu()==null&&entity.getParentMenuItem()==null)
            throw new EntityValidateException("menu.item.father.error");
    }
}
