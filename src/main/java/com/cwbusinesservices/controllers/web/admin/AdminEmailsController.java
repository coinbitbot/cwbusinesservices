package com.cwbusinesservices.controllers.web.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Oleh on 01.08.2017.
 */
@Controller
@PreAuthorize("hasPermission(1,'CREATE_EMAIL_TEMPLATE,EDIT_EMAIL_TEMPLATE')")
@RequestMapping("/admin/emails")
public class AdminEmailsController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all() {
        return "admin/emails/all";
    }

    /*
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(
            Model model
    ) {
        model.addAttribute("id", 0);
        return "admin/emails/edit";
    }
    */

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("id", id);
        return "admin/emails/edit";
    }

}
