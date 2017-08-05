package com.cwbusinesservices.persistence.dao.repositories;

import com.cwbusinesservices.pojo.entities.PostEntity;

/**
 * Created by Andrii on 02.08.2017.
 */
public interface PostRepository extends BaseRepository<PostEntity,Integer>{

    PostEntity findOneByUrl(String url);

}
