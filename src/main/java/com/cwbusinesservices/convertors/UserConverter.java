package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.RequestCommentEntity;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import org.springframework.stereotype.Component;
import com.cwbusinesservices.pojo.entities.UserEntity;

import java.util.*;

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
        if(fields.contains(FIRST_NAME))
            map.put(FIRST_NAME, object.getFirstName());
        if(fields.contains(LAST_NAME))
            map.put(LAST_NAME, object.getLastName());
        if(fields.contains(PHONE))
            map.put(PHONE, object.getPhone());
        if(fields.contains(PASSWORD))
            map.put(PASSWORD, object.getPassword());
        if(fields.contains(ACTIVE))
            map.put(ACTIVE, object.isActive());
        if(fields.contains(ROLE))
            map.put(ROLE, object.getRoleEntity().getName());
        if(fields.contains(REQUESTS)&&object.getRequests()!=null){
            List<Integer> ids = new LinkedList<>();
            for (RequestEntity request:object.getRequests())
                ids.add(request.getId());
            map.put(REQUESTS,ids);
        }
        if(fields.contains(REQUESTS_COMMENTS)&&object.getRequestComments()!=null){
            List<Integer> ids = new LinkedList<>();
            for (RequestCommentEntity requestComment:object.getRequestComments())
                ids.add(requestComment.getId());
            map.put(REQUESTS_COMMENTS,ids);
        }
        return map;
    }
}
