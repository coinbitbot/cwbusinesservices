package com.cwbusinesservices.controllers.web.admin;

import com.cwbusinesservices.criteria.impl.BlogCategoryCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.services.blog.IBlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by Oleh on 04.08.2017.
 */
@Controller
@PreAuthorize("hasPermission(1,'CREATE_POST,EDIT_POST')")
@RequestMapping("/admin/posts")
public class AdminPostController {

    @Autowired
    private IBlogCategoryService blogCategoryService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(
            Model model
    ) {
        model.addAttribute("categories", getCategories());
        return "admin/posts/all";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("categories", getCategories());
        model.addAttribute("id", id);
        return "admin/posts/edit";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(
            Model model
    ) {
        model.addAttribute("categories", getCategories());
        model.addAttribute("id", 0);
        return "admin/posts/edit";
    }

    public List<Map<String, Object>> getCategories() {
        try {
            BlogCategoryCriteria criteria = new BlogCategoryCriteria();
            criteria.setLimit(0);

            return blogCategoryService.getList(criteria, new HashSet<>(Arrays.asList("id", "name")));
        } catch (BaseException e) {
            return new ArrayList<>();
        }
    }
}
