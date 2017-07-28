package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.criteria.impl.CompanyCriteria;
import com.cwbusinesservices.criteria.impl.InfoPageCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.services.company.ICompanyService;
import com.cwbusinesservices.services.infopages.IInfoPageService;
import com.cwbusinesservices.services.utils.Utils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by Oleh on 25.07.2017.
 */
@Controller
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private Utils utils;

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String catalog(
            Model model
    ) {
        formCatalog(1, model);

        return "companies/catalog";
    }

    @RequestMapping(value = "/catalog/{page}/page", method = RequestMethod.GET)
    public String catalog(
            @PathVariable("page") int page,
            Model model
    ) {
        if (page < 1) {
            return "redirect:/companies/catalog/1/page";
        }

        formCatalog(page, model);

        return "companies/catalog";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(
            @PathVariable("id") int id,
            Model model
    ) {
        try {
            model.addAttribute("company", companyService.getById(id, COMPANY_FIELDS));
        } catch (BaseException e) {
            // TODO: redirect to 404?
        }
        return "companies/view";
    }

    private void formCatalog(int page, Model model) {
        try {
            CompanyCriteria criteria = new CompanyCriteria(
                    COMPANIES_PER_PAGE * (page - 1),
                    COMPANIES_PER_PAGE,
                    null
            );
            criteria.setActive(true);

            try {
                List<Map<String, Object>> list = companyService.getList(criteria, CATALOG_COMPANY_FIELDS);
                int count = companyService.count(criteria);

                model.addAttribute("companies", list);
                model.addAttribute("total_number", count);
                model.addAttribute("number_of_pages", utils.numberOfPages(count, COMPANIES_PER_PAGE));
            } catch (BaseException e) {
                // TODO: redirect to 404 or just show warning message?
            }
        } catch (WrongRestrictionException e) {
            // can not be thrown as we did not pass string restriction
        }
    }

    private final Set<String> COMPANY_FIELDS = new HashSet<>(Arrays.asList(
            "id", "name", "text"
    ));
    private final Set<String> CATALOG_COMPANY_FIELDS = new HashSet<>(Arrays.asList(
            "id", "name"
    ));
    private final int COMPANIES_PER_PAGE = 10;
}
