package com.cwbusinesservices.persistence.dao.repositories;

import com.cwbusinesservices.pojo.entities.EmailSubscribeEntity;

/**
 * Created by Andrii on 31.07.2017.
 */
public interface EmailSubscribeRepository extends BaseRepository<EmailSubscribeEntity,Integer> {
    EmailSubscribeEntity findByEmail(String email);
}
