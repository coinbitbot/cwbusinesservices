package com.cwbusinesservices.persistence.dao.repositories;

import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;

/**
 * Created by Andrii on 31.07.2017.
 */
public interface EmailTemplateRepository extends BaseRepository<EmailTemplateEntity,Integer>{
    EmailTemplateEntity findByCode(EmailTemplateCodeEnum code);
}
