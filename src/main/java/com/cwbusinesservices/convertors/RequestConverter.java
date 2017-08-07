package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.IndustryEntity;
import com.cwbusinesservices.pojo.entities.InterestEntity;
import com.cwbusinesservices.pojo.entities.RequestCommentEntity;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.cwbusinesservices.convertors.Fields.Request.*;

/**
 * Created by Andrii on 29.07.2017.
 */
@Component
public class RequestConverter extends Converter<RequestEntity>{
    @Override
    public Map<String, Object> convert(RequestEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();
        if(fields.contains(ID))
            map.put(ID, object.getId());
        if (fields.contains(USER_ID)&&object.getUser()!=null)
            map.put(USER_ID,object.getUser().getId());
        if (fields.contains(COMMENTS)&&object.getRequestComments()!=null){
            List<Integer> ids = new LinkedList<Integer>();
            for (RequestCommentEntity entity:object.getRequestComments())
                ids.add(entity.getId());
            map.put(COMMENTS,ids);
        }
        if (fields.contains(COMPANY_NAME))
            map.put(COMPANY_NAME,object.getCompanyName());
        if (fields.contains(HAS_FILE))
            map.put(HAS_FILE,object.isHasFile());
        if (fields.contains(STATUS))
            map.put(STATUS,object.getStatus());
        if (fields.contains(INTEREST_ALTER))
            map.put(INTEREST_ALTER,object.getInterestAlter());

        IndustryEntity industry = object.getIndustry();
        if (industry != null) {
            if (fields.contains(INDUSTRY))
                map.put(INDUSTRY, industry.getId());
            if (fields.contains(INDUSTRY_NAME))
                map.put(INDUSTRY_NAME, industry.getName());
        }
        List<InterestEntity> interests = object.getInterests();
        if (interests != null) {
            if (fields.contains(INTERESTS)) {
                List<Integer> ids = new LinkedList<>();
                for (InterestEntity entity : interests)
                    ids.add(entity.getId());
                map.put(INTERESTS, ids);
            }
            if (fields.contains(INTERESTS_NAME)) {
                List<String> names = new LinkedList<>();
                for (InterestEntity entity : interests)
                    names.add(entity.getName());
                map.put(INTERESTS_NAME, names);
            }
        }

        return map;
    }
}
