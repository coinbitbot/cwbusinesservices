package com.cwbusinesservices.persistence.dao.repositories;

import com.cwbusinesservices.pojo.entities.PermissionEntity;

/**
 * Created by Oleh on 29.07.2017.
 */
public interface PermissionsRepository extends BaseRepository<PermissionEntity, Integer> {

    PermissionEntity findByName(String name);

}
