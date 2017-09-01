package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.pojo.view.ContactUsView;
import com.cwbusinesservices.services.mailing.IMailingService;
import com.cwbusinesservices.services.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by Oleh on 01.09.2017.
 */
@Controller
@RequestMapping("/api/mail")
public class MailApiController {

    @Autowired
    private IMailingService mailingService;

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private IUserService userService;

    @RequestMapping(
            value = "/contact_us",
            method = RequestMethod.POST
    )
    public @ResponseBody
    Response<Boolean> contactUs(
            @RequestBody ContactUsView view
    ) throws BaseException {
        boolean result = mailingService.sendEmailToUser(
                EmailTemplateCodeEnum.EMAIL_FROM_CONTACT_PAGE,
                "oleh.kurpiak@gmail.com",
                new HashMap<String, String>(){{
                    put("USER_NAME", view.getName());
                    put("EMAIL", view.getEmail());
                    put("TEXT", view.getText());
                }},
                LocaleContextHolder.getLocale()
        );

        return responseFactory.get(result);
    }

    @RequestMapping(
            value = "/set_password",
            method = RequestMethod.POST
    )
    public @ResponseBody
    Response<Boolean> sendActivationEmail(
            HttpServletResponse response,
            HttpServletRequest request
    ) throws BaseException {
        return responseFactory.get(userService.sendActivationLinkToUser(response, request));
    }

}
