package com.cwbusinesservices.services.menu;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.service_error.ActionNotAllowedException;
import com.cwbusinesservices.pojo.entities.MenuEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 01.08.2017.
 */
@Service
public class MenuValidationServiceImpl extends BaseValidator<MenuEntity,Integer> implements IMenuValidationService{

    public MenuValidationServiceImpl(){
        super(PermissionsEnum.CREATE_MENU,PermissionsEnum.EDIT_MENU,PermissionsEnum.DELETE_MENU,MenuEntity.class);
    }

    @Override
    public void validForCreate(MenuEntity entity) throws BaseException {
        throw new ActionNotAllowedException("menu.error.create");
    }

    @Override
    public void validForUpdate(MenuEntity entity) throws BaseException {
        throw new ActionNotAllowedException("menu.error.edit");
    }

    @Override
    public void validForDelete(MenuEntity entity) throws BaseException {
        throw new ActionNotAllowedException("menu.error.delete");
    }
}
