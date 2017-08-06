package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.criteria.impl.BlogCategoryCriteria;
import com.cwbusinesservices.criteria.impl.InfoPageCriteria;
import com.cwbusinesservices.criteria.impl.PostCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.services.blog.IBlogCategoryService;
import com.cwbusinesservices.services.blog.IPostService;
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
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private IBlogCategoryService blogCategoryService;

    @Autowired
    private IPostService postService;

    @Autowired
    private Utils utils;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String catalog(
            Model model
    ) {
        formCatalog(1, null, model);

        return "blog/catalog";
    }

    @RequestMapping(value = "/{page}/page", method = RequestMethod.GET)
    public String catalog(
            @PathVariable("page") int page,
            Model model
    ) {
        if (page < 1) {
            return "redirect:/blog/1/page";
        }

        formCatalog(page, null, model);

        return "blog/catalog";
    }

    @RequestMapping(value = {"/{category}"}, method = RequestMethod.GET)
    public String catalog(
            @PathVariable("category") String category,
            Model model
    ) {
        formCatalog(1, category, model);

        return "blog/catalog";
    }

    @RequestMapping(value = "/{category}/{page}/page", method = RequestMethod.GET)
    public String catalog(
            @PathVariable("category") String category,
            @PathVariable("page") int page,
            Model model
    ) {
        if (page < 1) {
            return "redirect:/blog/1/page";
        }

        formCatalog(page, category, model);

        return "blog/catalog";
    }

    @RequestMapping(value = "/post/{url}", method = RequestMethod.GET)
    public String view(
            @PathVariable("url") String url,
            Model model
    ) {
        try {
            model.addAttribute("post", postService.getByUrl(url, VIEW_FIELDS));
        } catch (BaseException e) {
            // TODO: redirect to 404?
        }
        return "blog/view";
    }

    private void formCatalog(int page, String category, Model model) {
        try {
            PostCriteria criteria = new PostCriteria(
                    POST_PER_PAGE * (page - 1),
                    POST_PER_PAGE,
                    null
            );
            criteria.setCategory_code(category);

            try {
                List<Map<String, Object>> list = postService.getList(criteria, CATALOG_FIELDS);
                model.addAttribute("posts", list);
            } catch (BaseException e) { }

            int count = postService.count(criteria);
            long numberOfPages = utils.numberOfPages(count, POST_PER_PAGE);
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

            if (category != null) {
                try {
                    Map<String, Object> currentCategory = blogCategoryService.getByCode(category, CATEGORY_CATALOG_FIELDS);
                    model.addAttribute("current_category", currentCategory);
                } catch (BaseException e) {}
            }

            model.addAttribute("categories", getSortedCategories());
        } catch (WrongRestrictionException e) {
            // can not be thrown as we did not pass string restriction
        }
    }

    private List<Map<String, Object>> getSortedCategories() {
        try {
            BlogCategoryCriteria criteria = new BlogCategoryCriteria(0, 0, null);
            criteria.setOrder_by("position");

            return blogCategoryService.getList(criteria, CATEGORY_CATALOG_FIELDS);
        } catch (BaseException e) {

        }

        return new ArrayList<>();
    }

    private final Set<String> VIEW_FIELDS = new HashSet<>(Arrays.asList(
            "id", "title", "date", "has_img", "meta_title", "meta_description", "meta_keywords", "post_text", "category_code", "category_name"
    ));
    private final Set<String> CATALOG_FIELDS = new HashSet<>(Arrays.asList(
            "id", "url", "title", "date", "has_img", "short_description", "category_code", "category_name"
    ));
    private final Set<String> CATEGORY_CATALOG_FIELDS = new HashSet<>(Arrays.asList(
            "code", "name", "position"
    ));
    private final int POST_PER_PAGE = 3;
}
