package com.cwbusinesservices.controllers.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Oleh on 05.08.2017.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/profile")
public class UserProfileController {

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String profile() {
        return "profile/profile";
    }
}
