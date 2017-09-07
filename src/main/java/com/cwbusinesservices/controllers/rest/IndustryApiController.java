package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.criteria.impl.IndustryCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.IndustryEntity;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.view.IndustryView;
import com.cwbusinesservices.services.industry.IIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Andrii on 29.07.2017.
 */
@Controller
@RequestMapping(value = "/api/industry")
public class IndustryApiController extends BaseApiController<IndustryEntity,IndustryView,Integer>{

    @Autowired
    private IIndustryService industryService;

    @RequestMapping(
            value = "/swap",
            method = RequestMethod.POST
    )
    public @ResponseBody
    Response<Boolean>
    swap(
            @RequestBody IndustryCriteria criteria
    ) throws BaseException {
        return responseFactory.get(industryService.swap(criteria));
    }

}
