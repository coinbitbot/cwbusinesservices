package com.cwbusinesservices.services.sitemap;

import com.cwbusinesservices.criteria.impl.CompanyCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.services.blog.IBlogCategoryService;
import com.cwbusinesservices.services.company.ICompanyService;
import com.cwbusinesservices.services.employees.IEmployeeService;
import com.cwbusinesservices.services.service.IServiceService;
import com.cwbusinesservices.services.service.ServiceServiceImpl;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by Andrii on 09.09.2017.
 */
@Service
public class SitemapService {
    private static final String BASE_URL = "http://charlesworthbgs.com";

    @Autowired
    private IServiceService services;
    @Autowired
    private ICompanyService company;
    @Autowired
    private IBlogCategoryService blogCategory;
    @Autowired
    private IEmployeeService employee;

    public String createSitemap() throws MalformedURLException {
        WebSitemapGenerator sitemap = new WebSitemapGenerator(BASE_URL);

        //Add services page
        sitemap.addUrl(BASE_URL + "/services");
        //Add companies
        sitemap.addUrl(BASE_URL + "/companies/catalog");
        CompanyCriteria criteria = new CompanyCriteria();
        criteria.setActive(true);

        try {
            List<CompanyEntity> companies = null;
            companies = company.getList(criteria);
            for (CompanyEntity entry : companies)
                sitemap.addUrl(BASE_URL + "/companies/" + entry.getId());
        } catch (BaseException e) {

        }

        return String.join("", sitemap.writeAsStrings());
    }
}
