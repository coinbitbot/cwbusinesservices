package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.convertors.Fields;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.BlockEntity;
import com.cwbusinesservices.pojo.enums.BlockCodesEnum;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.pojo.view.BlockView;
import com.cwbusinesservices.services.blocks.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 28.07.2017.
 */
@Controller
@RequestMapping(value = "/api/block")
public class BlockApiController extends BaseApiController<BlockEntity,BlockView,Integer>{

    @Autowired
    private IBlockService service;
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
            @PathVariable("code") BlockCodesEnum code,
            @RequestParam(value = "fields", required = false, defaultValue = Fields.DEFAULT) Set<String> fields
    ) throws BaseException {
        return responseFactory.get(service.getByCode(code, fields));
    }
}
