package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.PostEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static com.cwbusinesservices.convertors.Fields.Post.*;
/**
 * Created by Andrii on 02.08.2017.
 */
@Component
public class PostConverter extends Converter<PostEntity>{
    @Override
    public Map<String, Object> convert(PostEntity object, Set<String> fields) {
        Map<String,Object> map = new HashMap<>();
        if (fields.contains(ID))
            map.put(ID,object.getId());
        if (fields.contains(TITLE))
            map.put(TITLE,object.getTitle());
        if (fields.contains(URL))
            map.put(URL,object.getUrl());
        if (fields.contains(SHORT_DESCRIPTION))
            map.put(SHORT_DESCRIPTION, object.getShortDescription());
        if (fields.contains(DATE))
            map.put(DATE,object.getDate().getTime());
        if (fields.contains(HAS_IMG))
            map.put(HAS_IMG,object.getHasImg());
        if (fields.contains(META_TITLE))
            map.put(META_TITLE,object.getMetaTitle());
        if (fields.contains(META_DESCRIPTION))
            map.put(META_DESCRIPTION,object.getMetaDescription());
        if (fields.contains(META_KEYWORDS))
            map.put(META_KEYWORDS,object.getMetaKeywords());
        if (fields.contains(CATEGORY)&&object.getCategory()!=null)
            map.put(CATEGORY,object.getCategory().getId());
        if (fields.contains(POST_TEXT))
            map.put(POST_TEXT,object.getPostText());
        return map;
    }
}
