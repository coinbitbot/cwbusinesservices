package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.convertors.Converter;
import com.cwbusinesservices.pojo.entities.InterestEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import static com.cwbusinesservices.convertors.Fields.Interest.*;

/**
 * Created by Andrii on 29.07.2017.
 */
@Component
public class InterestConverter extends Converter<InterestEntity>{
    @Override
    public Map<String, Object> convert(InterestEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(NAME))
            map.put(NAME, object.getName());
        return map;
    }
}
