package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Oleh on 05.08.2017.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private SessionUtils sessionUtils;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String profile(Model model) {
        model.addAttribute("current_user", sessionUtils.getCurrentUser());
        return "profile/profile";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("current_user", sessionUtils.getCurrentUser());
        return "profile/edit";
    }
}
