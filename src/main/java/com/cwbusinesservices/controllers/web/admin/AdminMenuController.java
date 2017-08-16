package com.cwbusinesservices.controllers.web.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Oleh on 16.08.2017.
 */
@Controller
@PreAuthorize("hasPermission(1,'CREATE_MENU,EDIT_MENU,CREATE_MENU_ITEM,EDIT_MENU_ITEM')")
@RequestMapping("/admin/menu")
public class AdminMenuController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all() {
        return "admin/menu/all";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("id", id);
        return "admin/menu/edit";
    }

}
