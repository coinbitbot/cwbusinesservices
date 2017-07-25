package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.convertors.Fields;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.InfoPageEntity;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.pojo.view.InfoPageView;
import com.cwbusinesservices.services.infopages.IInfoPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 25.07.2017.
 */
@Controller
@RequestMapping(value = "/api/infopage")
public class InfoPageApiController
        extends BaseApiController<InfoPageEntity, InfoPageView, Integer> {

    @Autowired
    private IInfoPageService service;
    @Autowired
    private ResponseFactory responseFactory;


    @RequestMapping(
            value = "/url/{url}",
            method = RequestMethod.GET
    )
    public
    @ResponseBody
    Response<Map<String, Object>>
    getByUrl(
            @PathVariable("url") String url,
            @RequestParam(value = "fields", required = false, defaultValue = Fields.InfoPage.DEFAULT) Set<String> fields
    ) throws BaseException {
        return responseFactory.get(service.getByUrl(url, fields));
    }




}
