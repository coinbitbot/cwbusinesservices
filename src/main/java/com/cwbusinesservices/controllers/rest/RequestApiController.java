package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.convertors.Fields;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import com.cwbusinesservices.pojo.enums.RequestStatusEnum;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.view.RequestView;
import com.cwbusinesservices.services.request.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 29.07.2017.
 */
@Controller
@RequestMapping(value = "/api/request")
public class RequestApiController extends BaseApiController<RequestEntity,RequestView,Integer>{

    @Autowired
    private IRequestService requestService;

    @RequestMapping(
            value = "/status",
            method = RequestMethod.POST
    )
    public
    @ResponseBody
    Response<Boolean>
    getByCode(
            @RequestBody RequestView view
    ) throws BaseException {
        return responseFactory.get(requestService.changeStatus(view));
    }
}
