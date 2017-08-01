package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.pojo.entities.MenuItemEntity;
import com.cwbusinesservices.pojo.view.MenuItemView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrii on 01.08.2017.
 */
@Controller
@RequestMapping(value = "/api/menu/item")
public class MenuItemApiController extends BaseApiController<MenuItemEntity,MenuItemView,Integer>{
}
