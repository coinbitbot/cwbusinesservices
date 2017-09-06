package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.CarouselImageEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.cwbusinesservices.convertors.Fields.CarouselImage.*;

/**
 * Created by Andrii on 29.08.2017.
 */
@Component
public class CarouselImageConverter extends Converter<CarouselImageEntity>{
    @Override
    public Map<String, Object> convert(CarouselImageEntity object, Set<String> fields) {
        Map<String,Object> map = new HashMap<>();
        if (fields.contains(ID))
            map.put(ID,object.getId());
        if (fields.contains(POSITION))
            map.put(POSITION,object.getPosition());
        if (fields.contains(DESCRIPTION))
            map.put(DESCRIPTION,object.getDescription());
        if (fields.contains(HAS_IMG))
            map.put(HAS_IMG, object.isHasFile());
        return map;
    }
}
