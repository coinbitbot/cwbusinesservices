package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.criteria.impl.IndustryCriteria;
import com.cwbusinesservices.criteria.impl.InterestCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.services.industry.IIndustryService;
import com.cwbusinesservices.services.interests.IInterestsService;
import com.cwbusinesservices.services.request.IRequestService;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oleh on 22.08.2017.
 */

@Controller
@RequestMapping("/requests")
@PreAuthorize("hasPermission(1,'CREATE_REQUEST,EDIT_REQUEST,VIEW_REQUEST')")
public class RequestsController {

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private IRequestService requestService;

    @Autowired
    private IInterestsService interestsService;

    @Autowired
    private IIndustryService industryService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("current_user", sessionUtils.getCurrentUser());
        try {
            model.addAttribute("industries", industryService.getList(new IndustryCriteria()));
            model.addAttribute("interests", interestsService.getList(new InterestCriteria()));
        } catch (BaseException e) {

        }
        return "requests/create";
    }

    @RequestMapping(value = "/{id}/chat", method = RequestMethod.GET)
    public String chat(
            @PathVariable("id") int id,
            Model model
    ) {
        try {
            model.addAttribute("request", requestService.getById(id, REQUEST_FIELDS));
        } catch (Exception e) {
            return "requests/no-requests";
        }

        model.addAttribute("current_user", sessionUtils.getCurrentUser());

        return "requests/chat";
    }

    private final Set<String> REQUEST_FIELDS = new HashSet<>(Arrays.asList(
            "id", "industry_name", "interest_alter", "interests_name", "company_name", "has_file", "status"
    ));

}
