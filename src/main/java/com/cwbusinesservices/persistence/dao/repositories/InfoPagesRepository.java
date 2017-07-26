package com.cwbusinesservices.persistence.dao.repositories;

import com.cwbusinesservices.pojo.entities.InfoPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by Andrii on 25.07.2017.
 */
public interface InfoPagesRepository extends BaseRepository<InfoPageEntity,Integer> {
    InfoPageEntity findByUrl(String url);
}
