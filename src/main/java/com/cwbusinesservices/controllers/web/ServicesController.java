package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.criteria.impl.InterestCriteria;
import com.cwbusinesservices.criteria.impl.ServiceCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.services.service.IServiceService;
import com.cwbusinesservices.services.utils.Utils;
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
@RequestMapping("/services")
public class ServicesController {

    @Autowired
    private IServiceService serviceService;

    @Autowired
    private Utils utils;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String catalog(
            Model model
    ) {
        ServiceCriteria criteria = new ServiceCriteria();
        criteria.setActive(true);
        criteria.setOrder_by("position");

        try {
            List<Map<String, Object>> list = serviceService.getList(criteria, CATALOG_FIELDS);
            model.addAttribute("services", list);
        } catch (BaseException e) { }

        return "services/catalog";
    }

    private void formCatalog(int page, Model model) {
        try {
            ServiceCriteria criteria = new ServiceCriteria(
                    PER_PAGE * (page - 1),
                    PER_PAGE,
                    null
            );
            criteria.setActive(true);

            try {
                List<Map<String, Object>> list = serviceService.getList(criteria, CATALOG_FIELDS);
                model.addAttribute("services", list);
            } catch (BaseException e) { }

            int count = serviceService.count(criteria);
            long numberOfPages = utils.numberOfPages(count, PER_PAGE);
            model.addAttribute("total_number", count);
            model.addAttribute("current_page", page);
            model.addAttribute("number_of_pages", numberOfPages);

            // create four closest pages to current
            int minPage = 1;
            int maxPage = (int)numberOfPages;
            if (page > 3)
                minPage = page - 2;
            if (maxPage - page > 2)
                maxPage = page + 2;
            model.addAttribute("min_page", minPage);
            model.addAttribute("max_page", maxPage);
        } catch (WrongRestrictionException e) {
            // can not be thrown as we did not pass string restriction
        }
    }

    private final Set<String> CATALOG_FIELDS = new HashSet<>(Arrays.asList(
            "id", "name", "description", "has_icon"
    ));
    private final int PER_PAGE = 5;
}
