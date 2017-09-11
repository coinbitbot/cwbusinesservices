package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.criteria.impl.EmployeeCriteria;
import com.cwbusinesservices.criteria.impl.InfoPageCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.services.employees.IEmployeeService;
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
//@RequestMapping("/info_pages")
public class InfoPageController {

    @Autowired
    private IInfoPageService infoPageService;

    @Autowired
    private Utils utils;

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping(value = "/info_pages/catalog", method = RequestMethod.GET)
    public String catalog(
            Model model
    ) {
        formCatalog(1, model);

        return "info_pages/catalog";
    }

    @RequestMapping(value = "/info_pages/catalog/{page}/page", method = RequestMethod.GET)
    public String catalog(
            @PathVariable("page") int page,
            Model model
    ) {
        if (page < 1) {
            return "redirect:/info_pages/catalog/1/page";
        }

        formCatalog(page, model);

        return "info_pages/catalog";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String employee(
            Model model
    ) {
        try {
            model.addAttribute("employees", employeeService.getList(new EmployeeCriteria()));
        } catch (BaseException e) {
            // TODO: redirect to 404?
        }
        return "info_pages/employees";
    }

    @RequestMapping(value = "/{page_id}", method = RequestMethod.GET)
    public String view(
            @PathVariable("page_id") String url,
            Model model
    ) {
        try {
            model.addAttribute("info_page", infoPageService.getByUrl(url, VIEW_PAGE_FIELDS));
            return "info_pages/view";
        } catch (BaseException e) {
            return "index/not_found";
        }

    }

    private void formCatalog(int page, Model model) {
        try {
            InfoPageCriteria criteria = new InfoPageCriteria(
                    INFO_PAGES_PER_PAGE * (page - 1),
                    INFO_PAGES_PER_PAGE,
                    null
            );

            try {
                List<Map<String, Object>> list = infoPageService.getList(criteria, CATALOG_PAGE_FIELDS);
                for (Map<String, Object> map : list) {
                    String text = (String)map.get("text");
                    if (text != null && !text.isEmpty()) {
                        text = Jsoup.parse(text).text();

                        if (text.length() > 160) {
                            text = text.substring(0, 160) + "...";
                        }
                    }
                    map.put("text", text);
                }
                int count = infoPageService.count(criteria);

                model.addAttribute("info_pages", list);
                model.addAttribute("total_number", count);
                model.addAttribute("number_of_pages", utils.numberOfPages(count, INFO_PAGES_PER_PAGE));
            } catch (BaseException e) {
                // TODO: redirect to 404 or just show warning message?
            }
        } catch (WrongRestrictionException e) {
            // can not be thrown as we did not pass string restriction
        }
    }

    private final Set<String> VIEW_PAGE_FIELDS = new HashSet<>(Arrays.asList(
            "header", "sub_header", "text", "meta_title", "meta_description", "meta_keywords"
    ));
    private final Set<String> CATALOG_PAGE_FIELDS = new HashSet<>(Arrays.asList(
            "url", "header", "sub_header", "text"
    ));
    private final int INFO_PAGES_PER_PAGE = 10;
}
