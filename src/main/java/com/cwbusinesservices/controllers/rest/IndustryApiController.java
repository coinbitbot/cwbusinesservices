package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.pojo.entities.IndustryEntity;
import com.cwbusinesservices.pojo.view.IndustryView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrii on 29.07.2017.
 */
@Controller
@RequestMapping(value = "/api/industry")
public class IndustryApiController extends BaseApiController<IndustryEntity,IndustryView,Integer>{
}
