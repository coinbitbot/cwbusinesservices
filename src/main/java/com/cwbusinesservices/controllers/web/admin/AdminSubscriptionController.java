package com.cwbusinesservices.controllers.web.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Oleh on 28.07.2017.
 */
@Controller
@PreAuthorize("hasPermission(1,'CREATE_EMAIL_SUBSCRIBE,EDIT_EMAIL_SUBSCRIBE')")
@RequestMapping("/admin/subscription")
public class AdminSubscriptionController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all() {
        return "admin/subscription/all";
    }

}
