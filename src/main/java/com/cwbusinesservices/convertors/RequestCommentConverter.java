package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.RequestCommentEntity;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static com.cwbusinesservices.convertors.Fields.RequestComment.*;
/**
 * Created by Andrii on 29.07.2017.
 */
@Component
public class RequestCommentConverter extends Converter<RequestCommentEntity>{

    @Override
    public Map<String, Object> convert(RequestCommentEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if (fields.contains(USER)&&object.getUser()!=null)
            map.put(USER,object.getUser().getId());
        if (fields.contains(REQUEST)&&object.getRequest()!=null)
            map.put(REQUEST,object.getRequest().getId());
        if (fields.contains(TEXT))
            map.put(TEXT,object.getText());
        if (fields.contains(HAS_FILE))
            map.put(HAS_FILE,object.isHasFile());
        if (fields.contains(DATE)&&object.getDate()!=null)
            map.put(DATE,object.getDate().getTime());
        return map;
    }
}
