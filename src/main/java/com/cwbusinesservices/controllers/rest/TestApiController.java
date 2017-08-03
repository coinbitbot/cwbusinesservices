package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.pojo.view.UserView;
import com.cwbusinesservices.services.mailing.IMailingService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

/**
 * Created by Andrii on 03.08.2017.
 */
@Controller
@RequestMapping(value = "/api/test")
public class TestApiController {

    @Autowired
    private IMailingService mailingService;
    @Autowired
    private ResponseFactory responseFactory;

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public
    @ResponseBody
    Response<Boolean>
    testEmailSend(
    ) throws BaseException {
        return responseFactory.get(mailingService.sendEmailToUser(EmailTemplateCodeEnum.EMAIL_SUBSCRIPTION,"glibovet@gmail.com",null, Locale.ENGLISH));
    }

}
