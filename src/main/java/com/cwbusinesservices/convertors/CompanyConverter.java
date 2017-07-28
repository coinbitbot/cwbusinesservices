package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.CompanyEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.cwbusinesservices.convertors.Fields.Company.*;

/**
 * Created by Andrii on 26.07.2017.
 */
@Component
public class CompanyConverter extends Converter<CompanyEntity>{
    @Override
    public Map<String, Object> convert(CompanyEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(ACTIVE))
            map.put(ACTIVE, object.isActive());
        if(fields.contains(HAS_IMG))
            map.put(HAS_IMG,object.isHasImage());
        if(fields.contains(TEXT))
            map.put(TEXT,object.getText());
        if (fields.contains(NAME))
            map.put(NAME,object.getName());
        return map;
    }
}
