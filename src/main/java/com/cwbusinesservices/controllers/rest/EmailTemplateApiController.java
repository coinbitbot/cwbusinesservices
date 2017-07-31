package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.convertors.Fields;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.pojo.view.EmailTemplateView;
import com.cwbusinesservices.services.templates.IEmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 31.07.2017.
 */
@Controller
@RequestMapping(value = "/api/email/template")
public class EmailTemplateApiController extends BaseApiController<EmailTemplateEntity,EmailTemplateView,Integer>{

    @Autowired
    private IEmailTemplateService service;
    @Autowired
    private ResponseFactory responseFactory;

    @RequestMapping(
            value = "/code/{code}",
            method = RequestMethod.GET
    )
    public
    @ResponseBody
    Response<Map<String, Object>>
    getByCode(
            @PathVariable("code") EmailTemplateCodeEnum code,
            @RequestParam(value = "fields", required = false, defaultValue = Fields.DEFAULT) Set<String> fields
    ) throws BaseException {
        return responseFactory.get(service.getByCode(code, fields));
    }
}
