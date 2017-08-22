package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.criteria.impl.RequestCriteria;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.services.request.IRequestService;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Oleh on 05.08.2017.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private IRequestService requestService;

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

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public String requests(Model model) {
        UserEntity current = sessionUtils.getCurrentUser();
        model.addAttribute("current_user", current);

        RequestCriteria criteria = new RequestCriteria();
        criteria.setUser_ids(Arrays.asList(current.getId()));
        try {
            model.addAttribute("requests", requestService.getList(criteria, REQUESTS_FIELDS));
        } catch (Exception e) { }

        return "profile/requests";
    }

    private final Set<String> REQUESTS_FIELDS = new HashSet<>(Arrays.asList(
            "id", "industry_name", "interest_alter", "interests_name", "company_name", "has_file", "status"
    ));
}
