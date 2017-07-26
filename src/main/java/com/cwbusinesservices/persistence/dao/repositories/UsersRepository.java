package com.cwbusinesservices.persistence.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cwbusinesservices.pojo.entities.UserEntity;

import java.util.List;

/**
 * Created by Andrii on 18.08.2016.
 */
@Transactional(propagation= Propagation.REQUIRED)
public interface UsersRepository extends BaseRepository<UserEntity,Integer>{
    public UserEntity findByEmail(String email);
}
