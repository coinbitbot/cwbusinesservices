package com.cwbusinesservices.persistence.dao.repositories;

import com.cwbusinesservices.pojo.entities.BlogCategoryEntity;

/**
 * Created by Andrii on 02.08.2017.
 */
public interface BlogCategoryRepository extends BaseRepository<BlogCategoryEntity,Integer>{
    BlogCategoryEntity findOneByCode(String code);
}
