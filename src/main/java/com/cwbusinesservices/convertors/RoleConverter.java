package com.cwbusinesservices.convertors;

import com.cwbusinesservices.pojo.entities.PermissionEntity;
import com.cwbusinesservices.pojo.entities.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.cwbusinesservices.convertors.Fields.Role.*;

/**
 * Created by Oleh on 29.07.2017.
 */
@Service
public class RoleConverter extends Converter<RoleEntity> {

    @Override
    public Map<String, Object> convert(RoleEntity object, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();

        if (fields.contains(ID))
            map.put(ID, object.getId());

        if (fields.contains(NAME))
            map.put(NAME, object.getName());

        if (fields.contains(PERMISSIONS)) {
            if (object.getPermissions() == null) {
                map.put(PERMISSIONS, null);
            } else {
                List<Map<String, Object>> permissions = new ArrayList<>();

                for (final PermissionEntity permission : object.getPermissions()) {
                    permissions.add(new HashMap<String, Object>(){{
                        put(ID, permission.getId());
                        put(NAME, permission.getName());
                    }});
                }

                map.put(PERMISSIONS, permissions);
            }
        }

        return map;
    }

}
