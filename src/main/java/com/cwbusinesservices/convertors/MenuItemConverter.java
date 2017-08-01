package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.MenuItemEntity;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.cwbusinesservices.convertors.Fields.MenuItem.*;

/**
 * Created by Andrii on 01.08.2017.
 */
@Component
public class MenuItemConverter extends Converter<MenuItemEntity>{
    @Override
    public Map<String, Object> convert(MenuItemEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(NAME))
            map.put(NAME,object.getName());
        if(fields.contains(MENU)&&object.getMenu()!=null)
            map.put(MENU,object.getMenu().getId());
        if(fields.contains(PARENT_MENU)&&object.getParentMenuItem()!=null)
            map.put(PARENT_MENU,object.getParentMenuItem().getId());
        if (fields.contains(CHILD_ITEMS)&&object.getChildItems()!=null){
            List<Integer> ids = new LinkedList<>();
            for (MenuItemEntity item:object.getChildItems())
                ids.add(item.getId());
            map.put(CHILD_ITEMS,ids);
        }
        if(fields.contains(URL))
            map.put(URL,object.getUrl());
        if (fields.contains(POSITION))
            map.put(POSITION,object.getPosition());
        return map;
    }
}
