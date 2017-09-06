package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.criteria.impl.InterestCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.InterestEntity;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.view.InterestView;
import com.cwbusinesservices.services.interests.IInterestsService;
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
@RequestMapping(value = "/api/interest")
public class InterestApiController extends BaseApiController<InterestEntity,InterestView,Integer>{

    @Autowired
    private IInterestsService interestsService;

    @RequestMapping(
            value = "/swap",
            method = RequestMethod.POST
    )
    public @ResponseBody
    Response<Boolean>
    swap(
            @RequestBody InterestCriteria criteria
    ) throws BaseException {
        return responseFactory.get(interestsService.swap(criteria));
    }

}
