package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.criteria.impl.ServiceCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.ServiceEntity;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.view.ServiceView;
import com.cwbusinesservices.services.service.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Andrii on 27.07.2017.
 */
@Controller
@RequestMapping(value = "/api/service")
public class ServiceApiController extends BaseApiController<ServiceEntity,ServiceView,Integer>{

    @Autowired
    private IServiceService serviceService;

    @RequestMapping(
            value = "/swap",
            method = RequestMethod.POST
    )
    public @ResponseBody
    Response<Boolean>
    swap(
            @RequestBody ServiceCriteria criteria
    ) throws BaseException {
        return responseFactory.get(serviceService.swap(criteria));
    }

}
