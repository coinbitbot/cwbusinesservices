package com.cwbusinesservices.persistence.dao.repositories;

import com.cwbusinesservices.pojo.entities.CompanyEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Andrii on 26.07.2017.
 */
public interface CompanyRepository extends BaseRepository<CompanyEntity,Integer>{
}
