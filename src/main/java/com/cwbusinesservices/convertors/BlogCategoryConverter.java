package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.BlogCategoryEntity;
import com.cwbusinesservices.pojo.entities.PostEntity;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.cwbusinesservices.convertors.Fields.BlogCategory.*;

/**
 * Created by Andrii on 02.08.2017.
 */
@Component
public class BlogCategoryConverter extends Converter<BlogCategoryEntity>{
    @Override
    public Map<String, Object> convert(BlogCategoryEntity object, Set<String> fields) {
        Map<String,Object> map = new HashMap<>();
        if (fields.contains(ID))
            map.put(ID, object.getId());
        if (fields.contains(NAME))
            map.put(NAME, object.getName());
        if (fields.contains(CODE))
            map.put(CODE,object.getCode());
        if (fields.contains(POSITION))
            map.put(POSITION,object.getPostition());
        if (fields.contains(POSTS)&&object.getPosts()!=null){
            List<Integer> ids = new LinkedList<>();
            for (PostEntity post:object.getPosts())
                ids.add(post.getId());
            map.put(POSTS,ids);
        }
        return map;
    }
}
