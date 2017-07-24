package com.cwbusinesservices.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.cwbusinesservices.convertors.Fields;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.pojo.view.UserView;
import com.cwbusinesservices.services.users.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
@Controller
@RequestMapping(value = "/api/users")
public class UserApiController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ResponseFactory responseFactory;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public
    @ResponseBody Response<Map<String, Object>>
    getUser(
            @PathVariable("id") int userId,
            @RequestParam(value = "fields", required = false, defaultValue = Fields.User.DEFAULT) Set<String> fields
    ) throws BaseException {
        return responseFactory.get(userService.getUserByIdMap(userId, fields));
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public @ResponseBody Response<List<Map<String, Object>>>
    getUsers(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(value = "fields", required = false, defaultValue = Fields.User.DEFAULT) Set<String> fields,
            @RequestParam(value = "restrict", required = false) String restrict
    ) throws BaseException {
        return responseFactory.get(userService.getUsersMap(offset, limit, fields, restrict));
    }

    @RequestMapping(
            value = "/count",
            method = RequestMethod.GET
    )
    public @ResponseBody Response<Integer>
    countUsers(
            @RequestParam(value = "restrict", required = false) String restrict
    ) throws BaseException {
        return responseFactory.get(userService.count(restrict));
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT
    )
    public
    @ResponseBody Response<Integer>
    createUser(
            @RequestBody UserView view
    ) throws BaseException {
        return responseFactory.get(userService.create(view));
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    public
    @ResponseBody Response<Boolean>
    update(
            @RequestBody UserView view
    ) throws BaseException {
        return responseFactory.get(userService.update(view));
    }

    @RequestMapping(
            value = "/sign_in",
            method = RequestMethod.POST
    )
    public
    @ResponseBody Response<Boolean>
    signIn(
            @RequestBody UserView view
    ) throws BaseException {
        return responseFactory.get(userService.signInUser(view));
    }

    @RequestMapping(
            value = "/logout",
            method = RequestMethod.POST
    )
    public
    @ResponseBody Response<Boolean>
    logout(HttpServletRequest request, HttpServletResponse response){
        return responseFactory.get(userService.logoutUser(request, response));
    }
}
