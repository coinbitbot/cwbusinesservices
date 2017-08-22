package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.pojo.entities.UserEntity;
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
public class UserApiController extends BaseApiController<UserEntity, UserView, Integer> {

    @Autowired
    private IUserService userService;

    @Autowired
    private ResponseFactory responseFactory;


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

    @RequestMapping(
            value = "/password",
            method = RequestMethod.POST
    )
    public
    @ResponseBody Response<Boolean>
    changePassword(
            @RequestBody UserView view
    ) throws BaseException {
        return responseFactory.get(userService.changePassword(view));
    }
}
