package com.cwbusinesservices.services.menu;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.MenuEntity;
import com.cwbusinesservices.pojo.enums.MenuCodeEnum;
import com.cwbusinesservices.pojo.view.MenuView;
import com.cwbusinesservices.services.BaseService;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 01.08.2017.
 */
public abstract class IMenuService extends BaseService<MenuEntity,MenuView,Integer>{
    public IMenuService(){
        super(MenuEntity.class);
    }

    public abstract Map<String,Object> getByCode(MenuCodeEnum code, Set<String> fields) throws BaseException;
}
