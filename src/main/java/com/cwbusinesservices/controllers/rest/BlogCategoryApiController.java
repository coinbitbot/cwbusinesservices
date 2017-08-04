package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.convertors.Fields;
import com.cwbusinesservices.criteria.impl.BlogCategoryCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.BlogCategoryEntity;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.pojo.view.BlogCategoryView;
import com.cwbusinesservices.services.blog.IBlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 02.08.2017.
 */
@Controller
@RequestMapping(value = "/api/blog/category")
public class BlogCategoryApiController extends BaseApiController<BlogCategoryEntity,BlogCategoryView,Integer> {
    @Autowired
    private IBlogCategoryService service;
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
            @PathVariable("code") String code,
            @RequestParam(value = "fields", required = false, defaultValue = Fields.DEFAULT) Set<String> fields
    ) throws BaseException {
        return responseFactory.get(service.getByCode(code, fields));
    }

    @RequestMapping(
            value = "/swap",
            method = RequestMethod.POST
    )
    public @ResponseBody Response<Boolean>
    swap(
            @RequestBody BlogCategoryCriteria criteria
    ) throws BaseException {
        return responseFactory.get(service.swap(criteria));
    }
}
