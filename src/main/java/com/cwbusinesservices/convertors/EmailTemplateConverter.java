package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.cwbusinesservices.convertors.Fields.EmailTemplate.*;

/**
 * Created by Andrii on 31.07.2017.
 */
@Component
public class EmailTemplateConverter extends Converter<EmailTemplateEntity>{
    @Override
    public Map<String, Object> convert(EmailTemplateEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(CODE))
            map.put(CODE, object.getCode());
        if(fields.contains(TEXT))
            map.put(TEXT, object.getText());
        if(fields.contains(SUBJECT))
            map.put(SUBJECT,object.getSubject());
        return map;
    }
}
