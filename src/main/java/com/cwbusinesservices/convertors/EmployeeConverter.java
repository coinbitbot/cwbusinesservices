package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.EmployeeEntity;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.MediaSize;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.cwbusinesservices.convertors.Fields.Employee.*;
/**
 * Created by Andrii on 29.08.2017.
 */
@Component
public class EmployeeConverter extends Converter<EmployeeEntity>{
    @Override
    public Map<String, Object> convert(EmployeeEntity object, Set<String> fields) {
        Map<String,Object> map = new HashMap<>();
        if (fields.contains(ID))
            map.put(ID,object.getId());
        if (fields.contains(NAME))
            map.put(NAME, object.getName());
        if (fields.contains(POSITION))
            map.put(POSITION,object.getPosition());
        if (fields.contains(DESCRIPTION))
            map.put(DESCRIPTION,object.getDescription());
        if (fields.contains(EMAIL))
            map.put(EMAIL,object.getEmail());
        if (fields.contains(PHONE))
            map.put(PHONE,object.getPhone());
        if (fields.contains(HAS_IMG))
            map.put(HAS_IMG, object.isHasFile());
        return map;
    }
}
