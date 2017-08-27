package com.cwbusinesservices.persistence.dao.repositories;

import com.cwbusinesservices.pojo.entities.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Andrii on 02.08.2017.
 */
public interface PostRepository extends BaseRepository<PostEntity,Integer> {

    PostEntity findOneByUrl(String url);

    @Query("SELECT p FROM PostEntity p WHERE p.date > :date")
    List<PostEntity> findFirstByDateAfter(@Param("date") Date date);

    @Query("SELECT p FROM PostEntity p WHERE p.date < :date ORDER BY p.date DESC")
    List<PostEntity> findFirstByDateBefore(@Param("date") Date date);
}