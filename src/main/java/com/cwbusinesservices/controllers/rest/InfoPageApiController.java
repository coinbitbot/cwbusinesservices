package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.convertors.Fields;
import com.cwbusinesservices.exceptions.BaseException;
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
public class InfoPageApiController {
    @Autowired
    private IInfoPageService service;
    @Autowired
    private ResponseFactory responseFactory;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public
    @ResponseBody
    Response<Map<String, Object>>
    get(
            @PathVariable("id") int id,
            @RequestParam(value = "fields", required = false, defaultValue = Fields.InfoPage.DEFAULT) Set<String> fields
    ) throws BaseException {
        return responseFactory.get(service.getById(id, fields));
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public @ResponseBody Response<List<Map<String, Object>>>
    getList(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(value = "fields", required = false, defaultValue = Fields.InfoPage.DEFAULT) Set<String> fields,
            @RequestParam(value = "restrict", required = false) String restrict
    ) throws BaseException {
        return responseFactory.get(service.getList(offset, limit, fields, restrict));
    }

    @RequestMapping(
            value = "/count",
            method = RequestMethod.GET
    )
    public @ResponseBody Response<Integer>
    count(
            @RequestParam(value = "restrict", required = false) String restrict
    ) throws BaseException {
        return responseFactory.get(service.count(restrict));
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT
    )
    public
    @ResponseBody Response<Integer>
    create(
            @RequestBody InfoPageView view
    ) throws BaseException {
        return responseFactory.get(service.create(view));
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    public
    @ResponseBody Response<Boolean>
    update(
            @RequestBody InfoPageView view
    ) throws BaseException {
        return responseFactory.get(service.update(view));
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public
    @ResponseBody Response<Boolean>
    delete(
            @PathVariable("id") int id
    ) throws BaseException {
        return responseFactory.get(service.delete(id));
    }
}
