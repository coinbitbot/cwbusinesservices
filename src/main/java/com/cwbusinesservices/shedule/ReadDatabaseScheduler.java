package com.cwbusinesservices.shedule;

import com.cwbusinesservices.criteria.impl.*;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.BlockEntity;
import com.cwbusinesservices.pojo.enums.BlockCodesEnum;
import com.cwbusinesservices.pojo.enums.OrderDirectionEnum;
import com.cwbusinesservices.services.blocks.IBlockService;
import com.cwbusinesservices.services.blog.IPostService;
import com.cwbusinesservices.services.company.ICompanyService;
import com.cwbusinesservices.services.service.IServiceService;
import com.cwbusinesservices.services.testimonial.ITestimonialService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Oleh on 11.09.2017.
 */
@Service
public class ReadDatabaseScheduler implements InitializingBean {

    @Autowired
    private IServiceService serviceService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private ITestimonialService testimonialService;

    @Autowired
    private IBlockService blockService;

    @Autowired
    private IPostService postService;

    @Override
    public void afterPropertiesSet() throws Exception {
        sync();
    }

    public void sync(){
        try {
            BLOCKS = blockService.getList(new BlockCriteria());
        } catch (BaseException e) {
            BLOCKS = new ArrayList<>();
        }

        Set<BlockCodesEnum> uniqueCodes = BLOCKS.stream()
                .map(BlockEntity::getCode).collect(Collectors.toSet());

        if (uniqueCodes.contains(BlockCodesEnum.SERVICES)) {
            ServiceCriteria serviceCriteria = new ServiceCriteria();
            serviceCriteria.setActive(true);
            serviceCriteria.setHas_img(true);
            try {
                SERVICES = serviceService.getList(serviceCriteria, SERVICES_FIELDS_FOR_INDEX);
            } catch (BaseException e) {
                SERVICES = new ArrayList<>();
            }
        } else {
            SERVICES = new ArrayList<>();
        }

        if (uniqueCodes.contains(BlockCodesEnum.COMPANIES)) {
            CompanyCriteria companyCriteria = new CompanyCriteria();
            companyCriteria.setActive(true);
            companyCriteria.setHas_img(true);
            try {
                COMPANIES = companyService.getList(companyCriteria, COMPANIES_FIELDS_FOR_INDEX);
            } catch (BaseException e) {
                COMPANIES = new ArrayList<>();
            }
        } else {
            COMPANIES = new ArrayList<>();
        }

        if (uniqueCodes.contains(BlockCodesEnum.TESTIMONIALS)) {
            TestimonialCriteria testimonialCriteria = new TestimonialCriteria();
            testimonialCriteria.setActive(true);
            try {
                TESTIMONIALS = testimonialService.getList(testimonialCriteria, TESTIMONIALS_FIELDS_FOR_INDEX);
            } catch (BaseException e) {
                TESTIMONIALS = new ArrayList<>();
            }
        } else {
            TESTIMONIALS = new ArrayList<>();
        }

        try {
            PostCriteria criteria = new PostCriteria(
                    0, 3, null
            );
            criteria.setOrder_direction(OrderDirectionEnum.DESC);
            criteria.setOrder_by("date");
            LAST_POSTS = postService.getList(criteria, POST_FIELDS_FOR_INDEX);
        } catch (BaseException e) {
            LAST_POSTS = new ArrayList<>();
        }
    }

    public List<Map<String, Object>> COMPANIES = new ArrayList<>();
    public List<Map<String, Object>> TESTIMONIALS = new ArrayList<>();
    public List<Map<String, Object>> SERVICES = new ArrayList<>();
    public List<Map<String, Object>> LAST_POSTS = new ArrayList<>();
    public List<BlockEntity> BLOCKS = new ArrayList<>();

    private final Set<String> SERVICES_FIELDS_FOR_INDEX = new HashSet<>(
            Arrays.asList("id", "name", "short_description")
    );

    private final Set<String> COMPANIES_FIELDS_FOR_INDEX = new HashSet<>(
            Arrays.asList("id", "name")
    );

    private final Set<String> TESTIMONIALS_FIELDS_FOR_INDEX = new HashSet<>(
            Arrays.asList("id", "name", "text")
    );

    private final Set<String> POST_FIELDS_FOR_INDEX = new HashSet<>(
            Arrays.asList("url", "title", "date", "short_description", "category_code", "category_name")
    );
}
