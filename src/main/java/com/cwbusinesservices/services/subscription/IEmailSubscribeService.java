package com.cwbusinesservices.services.subscription;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.EmailSubscribeEntity;
import com.cwbusinesservices.pojo.view.EmailSubscribeView;
import com.cwbusinesservices.services.BaseService;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 31.07.2017.
 */
public abstract class IEmailSubscribeService extends BaseService<EmailSubscribeEntity,EmailSubscribeView,Integer> {
    public IEmailSubscribeService(){
        super(EmailSubscribeEntity.class);
    }

    public abstract EmailSubscribeEntity getByEmail(String email) throws BaseException;
    public abstract Map<String,Object> getByEmail(String email, Set<String> fields) throws BaseException;
}
