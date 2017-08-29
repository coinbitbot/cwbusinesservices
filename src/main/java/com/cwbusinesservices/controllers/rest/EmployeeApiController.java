package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.pojo.entities.EmployeeEntity;
import com.cwbusinesservices.pojo.view.EmployeeView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrii on 29.08.2017.
 */
@Controller
@RequestMapping(value = "/api/employee")
public class EmployeeApiController extends BaseApiController<EmployeeEntity, EmployeeView, Integer>{
}
