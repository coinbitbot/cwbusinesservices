package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.criteria.impl.InfoPageCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.services.infopages.IInfoPageService;
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
@RequestMapping("/info_pages")
public class InfoPageController {

    @Autowired
    private IInfoPageService infoPageService;

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String catalog(
            Model model
    ) {
        formCatalog(1, model);

        return "";
    }

    @RequestMapping(value = "/catalog/{page}/page", method = RequestMethod.GET)
    public String catalog(
            @PathVariable("page") int page,
            Model model
    ) {
        formCatalog(page, model);

        return "";
    }

    @RequestMapping(value = "/{page_id}", method = RequestMethod.GET)
    public String view(
            @PathVariable("page_id") String url,
            Model model
    ) {
        try {
            model.addAttribute("info_page", infoPageService.getByUrl(url, VIEW_PAGE_FIELDS));
        } catch (BaseException e) {
            // TODO: redirect to 404?
        }
        return "info_pages/view";
    }

    private void formCatalog(int page, Model model) {
        try {
            InfoPageCriteria criteria = new InfoPageCriteria(
                    INFO_PAGES_PER_PAGE * (page - 1),
                    INFO_PAGES_PER_PAGE,
                    null
            );

            //infoPageService.getList()
        } catch (WrongRestrictionException e) {
            // can not be thrown as we did not pass string restriction
        }
    }

    private final Set<String> VIEW_PAGE_FIELDS = new HashSet<>(Arrays.asList(
            "header", "sub_header", "text", "meta_title", "meta_description", "meta_keywords"
    ));
    private final Set<String> CATALOG_PAGE_FIELDS = new HashSet<>(Arrays.asList(
            "url", "header", "sub_header"
    ));
    private final int INFO_PAGES_PER_PAGE = 1;
}
