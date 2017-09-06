package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.ServiceEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.cwbusinesservices.convertors.Fields.Service.*;


/**
 * Created by Andrii on 27.07.2017.
 */
@Component
public class ServiceConverter extends Converter<ServiceEntity>{
    @Override
    public Map<String, Object> convert(ServiceEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(ACTIVE))
            map.put(ACTIVE, object.isActive());
        if(fields.contains(HAS_IMG))
            map.put(HAS_IMG,object.isHasImage());
        if(fields.contains(DESCRIPTION))
            map.put(DESCRIPTION,object.getDescription());
        if(fields.contains(SHORT_DESCRIPTION))
            map.put(SHORT_DESCRIPTION,object.getShortDescription());
        if (fields.contains(NAME))
            map.put(NAME,object.getName());
        return map;
    }
}
