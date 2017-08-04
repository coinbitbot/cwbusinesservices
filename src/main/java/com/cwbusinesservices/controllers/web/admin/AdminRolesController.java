package com.cwbusinesservices.controllers.web.admin;

import com.cwbusinesservices.pojo.enums.PermissionsEnum;
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
@PreAuthorize("hasPermission(1,'ROLE_ADMIN')")
@RequestMapping("/admin/roles")
public class AdminRolesController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all() {
        return "admin/roles/all";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("id", id);
        model.addAttribute("permissions", PermissionsEnum.values());
        return "admin/roles/edit";
    }

}
