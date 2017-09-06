package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.IndustryEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import static com.cwbusinesservices.convertors.Fields.Industry.*;
/**
 * Created by Andrii on 29.07.2017.
 */
@Component
public class IndustryConverter extends Converter<IndustryEntity>{
    @Override
    public Map<String, Object> convert(IndustryEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(NAME))
            map.put(NAME, object.getName());
        if (fields.contains(POSITION))
            map.put(POSITION,object.getPosition());
        return map;
    }
}
