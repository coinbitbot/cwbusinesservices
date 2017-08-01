package com.cwbusinesservices.persistence.dao.repositories;

import com.cwbusinesservices.pojo.entities.MenuEntity;
import com.cwbusinesservices.pojo.enums.MenuCodeEnum;

/**
 * Created by Andrii on 01.08.2017.
 */
public interface MenuRepository extends BaseRepository<MenuEntity,Integer>{
    MenuEntity findByCode(MenuCodeEnum code);
}
