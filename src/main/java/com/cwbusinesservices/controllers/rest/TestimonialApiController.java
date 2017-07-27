package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.convertors.Fields;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.TestimonialEntity;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.pojo.view.TestimonialView;
import com.cwbusinesservices.services.infopages.IInfoPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 27.07.2017.
 */
@Controller
@RequestMapping(value = "/api/testimonial")
public class TestimonialApiController extends BaseApiController<TestimonialEntity,TestimonialView,Integer>{

}
