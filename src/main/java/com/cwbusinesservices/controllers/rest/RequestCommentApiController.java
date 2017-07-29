package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.pojo.entities.RequestCommentEntity;
import com.cwbusinesservices.pojo.view.RequestCommentView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrii on 29.07.2017.
 */
@Controller
@RequestMapping(value = "/api/request/comment")
public class RequestCommentApiController extends BaseApiController<RequestCommentEntity,RequestCommentView,Integer>{
}
