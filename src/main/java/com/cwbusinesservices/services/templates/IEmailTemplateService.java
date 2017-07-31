package com.cwbusinesservices.services.templates;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
import com.cwbusinesservices.pojo.view.EmailTemplateView;
import com.cwbusinesservices.services.BaseService;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 31.07.2017.
 */
public abstract class IEmailTemplateService extends BaseService<EmailTemplateEntity,EmailTemplateView,Integer>{
    public IEmailTemplateService(){
        super(EmailTemplateEntity.class);
    }
    public abstract EmailTemplateEntity getByCode(EmailTemplateCodeEnum code) throws BaseException;
    public abstract Map<String,Object> getByCode(EmailTemplateCodeEnum code, Set<String> fields) throws BaseException;
}
