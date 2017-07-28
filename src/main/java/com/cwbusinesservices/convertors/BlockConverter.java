package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.BlockEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.cwbusinesservices.convertors.Fields.Block.*;

/**
 * Created by Andrii on 28.07.2017.
 */
@Component
public class BlockConverter extends Converter<BlockEntity>{
    @Override
    public Map<String, Object> convert(BlockEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(CODE))
            map.put(CODE,object.getCode());
        if(fields.contains(TITLE))
            map.put(TITLE,object.getTitle());
        return map;
    }
}
