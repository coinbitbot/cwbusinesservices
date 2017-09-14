package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.EmailRequiredException;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.pojo.view.ContactUsView;
import com.cwbusinesservices.pojo.view.UserView;
import com.cwbusinesservices.services.mailing.IMailingService;
import com.cwbusinesservices.services.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                "cbs@charlesworth-group.com",
                new HashMap<String, String>(){{
                    put("USER_NAME", view.getName());
                    put("EMAIL", view.getEmail());
                    put("TEXT", view.getText());
                }}
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

    @RequestMapping(
            value = "/recover_password",
            method = RequestMethod.POST
    )
    public @ResponseBody
    Response<Boolean> recoverPassword(
            @RequestBody UserView view
    ) throws BaseException {
        return responseFactory.get(userService.sendForgetPasswordEmail(view));
    }

    @RequestMapping(
            value = "/{email_type}/email_fields",
            method = RequestMethod.GET
    )
    public @ResponseBody
    Response<List<Map<String, String>>> getEmailFields(
        @PathVariable("email_type") EmailTemplateCodeEnum type
    ) {
        return responseFactory.get(type.getEmailFields());
    }
}
