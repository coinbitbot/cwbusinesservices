package com.cwbusinesservices.services.menu;

import com.cwbusinesservices.pojo.entities.MenuItemEntity;
import com.cwbusinesservices.pojo.view.MenuItemView;
import com.cwbusinesservices.services.BaseService;

/**
 * Created by Andrii on 01.08.2017.
 */
public abstract class IMenuItemService extends BaseService<MenuItemEntity,MenuItemView,Integer>{
    public IMenuItemService(){
        super(MenuItemEntity.class);
    }
}
