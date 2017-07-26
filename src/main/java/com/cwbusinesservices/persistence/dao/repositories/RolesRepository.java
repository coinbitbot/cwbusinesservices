package com.cwbusinesservices.persistence.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cwbusinesservices.pojo.entities.RoleEntity;
import com.cwbusinesservices.pojo.enums.RolesEnum;

/**
 * Created by Andrii on 10.09.2016.
 */
public interface RolesRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(RolesEnum name);
}
