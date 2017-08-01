package com.cwbusinesservices.mergers;

import com.cwbusinesservices.persistence.dao.repositories.MenuItemRepository;
import com.cwbusinesservices.persistence.dao.repositories.MenuRepository;
import com.cwbusinesservices.pojo.entities.MenuEntity;
import com.cwbusinesservices.pojo.entities.MenuItemEntity;
import com.cwbusinesservices.pojo.view.MenuItemView;
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
public class MenuItemMerger implements Merger<MenuItemEntity,MenuItemView>{
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private Utils utils;

    @Override
    public void merge(MenuItemEntity entity, MenuItemView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if(!utils.notEmpty(view.getName())) entity.setName(view.getName());
        else view.setName(entity.getName());
        if (view.getMenu()!=null){
            MenuEntity menu = menuRepository.findOne(view.getMenu());
            entity.setMenu(menu);
        }else if (entity.getMenu()!=null){
            view.setMenu(entity.getMenu().getId());
        }
        if(view.getParent_menu_item()!=null){
            MenuItemEntity menuItemEntity = menuItemRepository.findOne(view.getParent_menu_item());
            entity.setParentMenuItem(menuItemEntity);
        }else if (entity.getParentMenuItem()!=null){
            view.setParent_menu_item(entity.getParentMenuItem().getId());
        }
        if (view.getChild_items()!=null){
            Set<MenuItemEntity> children = new HashSet<>();
            for (Integer item:view.getChild_items()){
                children.add(menuItemRepository.findOne(item));
            }
            entity.setChildItems(children);
        }else if (entity.getChildItems()!=null){
            List<Integer> ids = new ArrayList<>();
            for (MenuItemEntity item:entity.getChildItems())
                ids.add(item.getId());
            view.setChild_items(ids);
        }
        if(!utils.notEmpty(view.getUrl()))entity.setUrl(view.getUrl());
        else view.setUrl(entity.getUrl());
        if(view.getPosition()!=null)entity.setPosition(view.getPosition());
        else view.setPosition(entity.getPosition());
    }
}
