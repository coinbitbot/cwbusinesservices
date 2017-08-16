package com.cwbusinesservices.mergers;

import com.cwbusinesservices.convertors.Fields;
import com.cwbusinesservices.persistence.dao.repositories.MenuItemRepository;
import com.cwbusinesservices.pojo.entities.MenuEntity;
import com.cwbusinesservices.pojo.entities.MenuItemEntity;
import com.cwbusinesservices.pojo.view.MenuView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 01.08.2017.
 */
@Component
public class MenuMerger implements Merger<MenuEntity,MenuView>{

    @Autowired
    private MenuItemRepository repository;

    @Override
    public void merge(MenuEntity entity, MenuView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        /*if (view.getCode()!=null) entity.setCode(view.getCode());
        else view.setCode(entity.getCode());*/
        if (view.getName()!=null) entity.setName(view.getName());
        else view.setName(entity.getName());
        if (view.getMenu_items()!=null){
            Set<MenuItemEntity> menuItems = new HashSet<>();
            for (Integer id:view.getMenu_items()){
                menuItems.add(repository.findOne(id));
            }
            entity.setMenuItems(menuItems);
        }else if (entity.getMenuItems()!=null){
            List<Integer> ids = new ArrayList<>();
            for (MenuItemEntity ent:entity.getMenuItems())
                ids.add(ent.getId());
            view.setMenu_items(ids);
        }
    }
}
