package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.InfoPageEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static com.cwbusinesservices.convertors.Fields.InfoPage.*;
/**
 * Created by Andrii on 25.07.2017.
 */
@Component
public class InfoPageConverter extends Converter<InfoPageEntity>{
    @Override
    public Map<String, Object> convert(InfoPageEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(URL))
            map.put(URL, object.getUrl());
        if(fields.contains(HEADER))
            map.put(HEADER,object.getHeader());
        if(fields.contains(SUB_HEADER))
            map.put(SUB_HEADER,object.getSubHeader());
        if(fields.contains(TEXT))
            map.put(TEXT,object.getText());
        if(fields.contains(ACTIVE))
            map.put(ACTIVE, object.isActive());
        if(fields.contains(META_TITLE))
            map.put(META_TITLE,object.getMetaTitle());
        if(fields.contains(META_DESCRIPTION))
            map.put(META_DESCRIPTION,object.getMetaDescription());
        if(fields.contains(META_KEYWORDS))
            map.put(META_KEYWORDS,object.getMetaKeywords());
        return map;
    }
}
