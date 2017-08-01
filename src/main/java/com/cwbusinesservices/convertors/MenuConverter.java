package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.MenuEntity;
import com.cwbusinesservices.pojo.entities.MenuItemEntity;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.cwbusinesservices.convertors.Fields.Menu.*;

/**
 * Created by Andrii on 01.08.2017.
 */
@Component
public class MenuConverter extends Converter<MenuEntity>{
    @Override
    public Map<String, Object> convert(MenuEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(CODE))
            map.put(CODE,object.getCode());
        if (fields.contains(NAME))
            map.put(NAME, object.getName());
        if(fields.contains(MENU_ITEMS)&&object.getMenuItems()!=null) {
            List<Integer> ids = new LinkedList<Integer>();
            for (MenuItemEntity item:object.getMenuItems())
                ids.add(item.getId());
            map.put(MENU_ITEMS,ids);
        }
        return null;
    }
}
