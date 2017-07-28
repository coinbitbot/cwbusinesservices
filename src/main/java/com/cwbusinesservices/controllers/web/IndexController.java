package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.criteria.impl.CompanyCriteria;
import com.cwbusinesservices.criteria.impl.ServiceCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.services.company.ICompanyService;
import com.cwbusinesservices.services.service.IServiceService;
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
 * Created by Andrii on 27.07.2016.
 */
@Controller
public class IndexController {

    @Autowired
    private IServiceService serviceService;

    @Autowired
    private ICompanyService companyService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String indexPage(
            Model model
    ){
        ServiceCriteria serviceCriteria = new ServiceCriteria();
        serviceCriteria.setActive(true);
        serviceCriteria.setHas_img(true);
        try {
            model.addAttribute("services", serviceService.getList(serviceCriteria, SERVICES_FIELDS_FOR_INDEX));
        } catch (BaseException e) { }

        CompanyCriteria companyCriteria = new CompanyCriteria();
        companyCriteria.setActive(true);
        companyCriteria.setHas_img(true);
        try {
            model.addAttribute("companies", companyService.getList(companyCriteria, COMPANIES_FIELDS_FOR_INDEX));
        } catch (BaseException e) { }

        return "index/index";
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public String signUp(){
        return "auth/sign_up";
    }

    private final Set<String> SERVICES_FIELDS_FOR_INDEX = new HashSet<>(
            Arrays.asList("id", "name")
    );

    private final Set<String> COMPANIES_FIELDS_FOR_INDEX = new HashSet<>(
            Arrays.asList("id", "name")
    );
}
