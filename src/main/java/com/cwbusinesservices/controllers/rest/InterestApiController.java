package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.pojo.entities.InterestEntity;
import com.cwbusinesservices.pojo.view.InterestView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrii on 29.07.2017.
 */
@Controller
@RequestMapping(value = "/api/interest")
public class InterestApiController extends BaseApiController<InterestEntity,InterestView,Integer>{
}
