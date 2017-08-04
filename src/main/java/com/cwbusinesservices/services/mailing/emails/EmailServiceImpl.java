package com.cwbusinesservices.services.mailing.emails;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.pojo.enums.EmailFieldsEnum;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
import com.cwbusinesservices.services.mailing.IMailingService;
import com.cwbusinesservices.services.users.IUserService;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrii on 04.08.2017.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EmailServiceImpl implements IEmailService{

    @Autowired
    private IUserService userService;

    @Value("${host.url}")
    private String host;

    @Autowired
    private IMailingService mailingService;

    @Override
    public void sendRequestFinishedEmail(UserEntity user) throws BaseException{
        String url = host+"/helper/login/"+user.getEmail()+"/"+userService.getAuthorizationToken(user);
        Map<String,String> params = new HashMap<>();
        params.put(EmailFieldsEnum.LINK.toString(),url);
        if (user.getFirstName()!=null)
            params.put(EmailFieldsEnum.NAME.toString(),user.getFirstName());
        mailingService.sendEmailToUser(EmailTemplateCodeEnum.NEW_REQUEST_TO_USER,user.getEmail(),params,null);
    }
}
