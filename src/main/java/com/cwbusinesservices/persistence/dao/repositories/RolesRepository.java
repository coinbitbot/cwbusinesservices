package com.cwbusinesservices.persistence.dao.repositories;

import com.cwbusinesservices.pojo.entities.RoleEntity;
import com.cwbusinesservices.pojo.enums.RolesEnum;

/**
 * Created by Andrii on 10.09.2016.
 */
public interface RolesRepository extends BaseRepository<RoleEntity, Integer> {
    RoleEntity findByName(RolesEnum name);
}
