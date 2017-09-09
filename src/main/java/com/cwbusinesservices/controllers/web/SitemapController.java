package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.services.utils.SitemapView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

import static org.springframework.util.MimeTypeUtils.APPLICATION_XML_VALUE;

/**
 * Created by Andrii on 09.09.2017.
 */
@Controller
public class SitemapController {

    private final SitemapView view;

    @Autowired
    public SitemapController(SitemapView view) {
        this.view = Objects.requireNonNull(view);
    }

    @RequestMapping(path = "/sitemap.xml", produces = APPLICATION_XML_VALUE)
    public SitemapView create() {
        return view;
    }
}
