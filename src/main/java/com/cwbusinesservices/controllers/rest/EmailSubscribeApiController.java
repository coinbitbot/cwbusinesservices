package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.convertors.Fields;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.EmailSubscribeEntity;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.pojo.view.EmailSubscribeView;
import com.cwbusinesservices.services.blocks.IBlockService;
import com.cwbusinesservices.services.subscription.IEmailSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 31.07.2017.
 */
@Controller
@RequestMapping(value = "/api/subscription")
public class EmailSubscribeApiController extends BaseApiController<EmailSubscribeEntity,EmailSubscribeView,Integer>{

    @Autowired
    private IEmailSubscribeService service;
    @Autowired
    private ResponseFactory responseFactory;

    @RequestMapping(
            value = "/code/{code}",
            method = RequestMethod.GET
    )
    public
    @ResponseBody
    Response<Map<String, Object>>
    getByEmail(
            @PathVariable("email") String email,
            @RequestParam(value = "fields", required = false, defaultValue = Fields.DEFAULT) Set<String> fields
    ) throws BaseException {
        return responseFactory.get(service.getByEmail(email, fields));
    }
}
