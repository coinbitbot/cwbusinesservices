package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.pojo.entities.RoleEntity;
import com.cwbusinesservices.pojo.view.RoleView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Oleh on 29.07.2017.
 */
@Controller
@RequestMapping("/api/role")
public class RoleApiController extends BaseApiController<RoleEntity, RoleView, Integer>{

}
