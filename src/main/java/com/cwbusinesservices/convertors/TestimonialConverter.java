package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.TestimonialEntity;
import org.springframework.stereotype.Component;
import static com.cwbusinesservices.convertors.Fields.Testimonial.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 27.07.2017.
 */
@Component
public class TestimonialConverter extends Converter<TestimonialEntity>{
    @Override
    public Map<String, Object> convert(TestimonialEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(ACTIVE))
            map.put(ACTIVE, object.isActive());
        if(fields.contains(POSITION))
            map.put(POSITION,object.getPosition());
        if(fields.contains(TEXT))
            map.put(TEXT,object.getText());
        if (fields.contains(NAME))
            map.put(NAME,object.getName());
        return map;
    }
}
