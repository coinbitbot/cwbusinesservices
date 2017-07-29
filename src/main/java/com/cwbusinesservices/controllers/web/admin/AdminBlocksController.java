package com.cwbusinesservices.controllers.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Oleh on 28.07.2017.
 */
@Controller
@RequestMapping("/admin/blocks")
public class AdminBlocksController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all() {
        return "admin/blocks/all";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("id", id);
        return "admin/blocks/edit";
    }

}
