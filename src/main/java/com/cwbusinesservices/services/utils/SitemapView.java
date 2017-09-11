package com.cwbusinesservices.services.utils;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.services.sitemap.SitemapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Andrii on 09.09.2017.
 */
@Component
public class SitemapView extends AbstractView{

    private SitemapService service;

    @Autowired
    public SitemapView(SitemapService service) {
        this.service = Objects.requireNonNull(service);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException, BaseException {
        response.setContentType(MediaType.APPLICATION_XML_VALUE);

        try (Writer writer = response.getWriter()) {
            writer.append(service.createSitemap());
        }
    }
}
