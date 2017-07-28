package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.pojo.entities.ServiceEntity;
import com.cwbusinesservices.pojo.view.ServiceView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrii on 27.07.2017.
 */
@Controller
@RequestMapping(value = "/api/service")
public class ServiceApiController extends BaseApiController<ServiceEntity,ServiceView,Integer>{

}
