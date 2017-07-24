package com.cwbusinesservices.convertors;

import org.springframework.stereotype.Component;
import com.cwbusinesservices.pojo.entities.UserEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.cwbusinesservices.convertors.Fields.User.*;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
@Component
public class UserConverter extends Converter<UserEntity> {
    @Override
    public Map<String, Object> convert(UserEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if(fields.contains(EMAIL))
            map.put(EMAIL, object.getEmail());
        if(fields.contains(NAME))
            map.put(NAME, object.getName());
        if(fields.contains(PASSWORD))
            map.put(PASSWORD, object.getPassword());
        if(fields.contains(ACTIVE))
            map.put(ACTIVE, object.isActive());
        if(fields.contains(ROLE))
            map.put(ROLE, object.getRoleEntity().getName());
        return map;
    }
}
