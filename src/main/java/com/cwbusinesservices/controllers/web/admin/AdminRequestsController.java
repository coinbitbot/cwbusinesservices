package com.cwbusinesservices.controllers.web.admin;

import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin/requests")
@PreAuthorize("hasPermission(1,'CREATE_REQUEST,EDIT_REQUEST,VIEW_REQUEST')")
public class AdminRequestsController {

    @Autowired
    private SessionUtils sessionUtils;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all() {
        return "admin/requests/all";
    }

    @RequestMapping(value = "/{id}/chat", method = RequestMethod.GET)
    public String edit(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("current_user_id", sessionUtils.getCurrentUser().getId());
        model.addAttribute("id", id);
        return "admin/requests/chat";
    }

}
