package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.services.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrii on 04.08.2017.
 */
@Controller
@RequestMapping(value = "/helper")
public class HelperController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login/{email}/{token}", method = RequestMethod.GET)
    public String signUpFromLink(@PathVariable("email")String email,
                                 @PathVariable("token")String token,
                                 Model model) {
        try {
            boolean login = userService.signInUser(email,token);
            if (login)
                return "redirect:/profile";
        } catch (BaseException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
