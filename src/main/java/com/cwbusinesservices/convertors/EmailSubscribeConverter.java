package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.EmailSubscribeEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.cwbusinesservices.convertors.Fields.EmailSubscribe.*;

/**
 * Created by Andrii on 31.07.2017.
 */
@Component
public class EmailSubscribeConverter extends Converter<EmailSubscribeEntity>{
    @Override
    public Map<String, Object> convert(EmailSubscribeEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(EMAIL))
            map.put(EMAIL, object.getEmail());
        return map;
    }
}
