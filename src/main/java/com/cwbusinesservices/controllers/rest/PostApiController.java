package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.pojo.entities.PostEntity;
import com.cwbusinesservices.pojo.view.CompanyView;
import com.cwbusinesservices.pojo.view.PostView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrii on 02.08.2017.
 */@Controller
@RequestMapping(value = "/api/blog/post")
public class PostApiController extends BaseApiController<PostEntity,PostView,Integer> {
}
