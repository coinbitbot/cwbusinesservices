package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.criteria.impl.*;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongPasswordException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.entities.BlockEntity;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.pojo.enums.BlockCodesEnum;
import com.cwbusinesservices.pojo.view.UserView;
import com.cwbusinesservices.services.blocks.IBlockService;
import com.cwbusinesservices.services.blog.IPostService;
import com.cwbusinesservices.services.company.ICompanyService;
import com.cwbusinesservices.services.industry.IIndustryService;
import com.cwbusinesservices.services.interests.IInterestsService;
import com.cwbusinesservices.services.service.IServiceService;
import com.cwbusinesservices.services.testimonial.ITestimonialService;
import com.cwbusinesservices.services.users.IUserService;
import com.cwbusinesservices.services.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by Andrii on 27.07.2016.
 */
@Controller
public class IndexController {

    @Autowired
    private IServiceService serviceService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private ITestimonialService testimonialService;

    @Autowired
    private IBlockService blockService;

    @Autowired
    private IInterestsService interestsService;

    @Autowired
    private IIndustryService industryService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @Autowired
    private Utils utils;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String indexPage(
            Model model
    ){
        try {
            List<BlockEntity> blocks = blockService.getList(new BlockCriteria());
            model.addAttribute("blocks", blocks);
            Set<BlockCodesEnum> uniqueCodes = new HashSet<>();

            for (BlockEntity entity : blocks) {
                uniqueCodes.add(entity.getCode());
            }

            if (uniqueCodes.contains(BlockCodesEnum.SERVICES)) {
                ServiceCriteria serviceCriteria = new ServiceCriteria();
                serviceCriteria.setActive(true);
                serviceCriteria.setHas_img(true);
                try {
                    model.addAttribute("services", serviceService.getList(serviceCriteria, SERVICES_FIELDS_FOR_INDEX));
                } catch (BaseException e) { }
            }

            if (uniqueCodes.contains(BlockCodesEnum.COMPANIES)) {
                CompanyCriteria companyCriteria = new CompanyCriteria();
                companyCriteria.setActive(true);
                companyCriteria.setHas_img(true);
                try {
                    model.addAttribute("companies", companyService.getList(companyCriteria, COMPANIES_FIELDS_FOR_INDEX));
                } catch (BaseException e) { }
            }

            if (uniqueCodes.contains(BlockCodesEnum.TESTIMONIALS)) {
                TestimonialCriteria testimonialCriteria = new TestimonialCriteria();
                testimonialCriteria.setActive(true);
                try {
                    model.addAttribute("testimonials", testimonialService.getList(testimonialCriteria, TESTIMONIALS_FIELDS_FOR_INDEX));
                } catch (BaseException e) { }
            }

            try {
                PostCriteria criteria = new PostCriteria(
                        0, 3, null
                );
                model.addAttribute("posts", postService.getList(criteria, POST_FIELDS_FOR_INDEX));
            } catch (BaseException e) { }
        } catch (BaseException e) { }

        return "index/index";
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String signUp(Model model) {
        try {
            model.addAttribute("industries", industryService.getList(new IndustryCriteria()));
            model.addAttribute("interests", interestsService.getList(new InterestCriteria()));
        } catch (BaseException e) {

        }

        return "auth/register";
    }

    @RequestMapping(value = "/forbidden", method = RequestMethod.GET)
    public String forbidden() {
             return "index/forbidden";
    }

    @RequestMapping(value = "/social_network_bad", method = RequestMethod.GET)
    public String socialNetworkBad() {
        return "profile/can_not_login_sn";
    }

    @RequestMapping(value = "/facebook_success", method = RequestMethod.GET)
    public String faceBookSuccess() {
        return "profile/facebook_success";
    }

    @RequestMapping(value = "/facebook_success", method = RequestMethod.POST)
    public String faceBookSuccessPost(
            HttpServletRequest request
    ) {
        Gson gson = new Gson();

        try {
            String accessToken = request.getParameter("access_token");
            String appData = utils.readResponseBody(
                    "https://graph.facebook.com/debug_token?input_token=" + accessToken +
                            "&access_token=EAALW9H5ZAl2gBANtD9qPZARUntI1YQm1d8YPGWnnptmTKIjyUnDs6pB3sZBne3cZBbpx7ZCuGaNAlpwpwh63XWrDFF5cZCyPbpZAyoNrVwe1UfBX6CoFA1gjY4x2cxZAPgyjh8dpcqceiHRIaDqSQyivHAVz7skZCyZCWVprtfBw66pD0O34VOuoctMCZCl35MV2uwZD"
            );

            Map<String, Object> map = gson.fromJson(appData, MAP_RESPONSE_TYPE);
            Map<String, Object> data = (Map<String, Object>)map.get("data");

            if (data != null) {
                String id = data.get("user_id").toString();
                String userResponse = utils.readResponseBody(
                        "https://graph.facebook.com/v2.10/" + id + "?access_token=" + accessToken + "&fields=email"
                );
                map = gson.fromJson(userResponse, MAP_RESPONSE_TYPE);
                Object email = map.get("email");

                if (email != null && email instanceof String) {
                    UserEntity entity = userService.getByEmail(email.toString());

                    UserView view = new UserView();
                    view.setEmail(entity.getEmail());
                    view.setPassword(entity.getPassword());

                    boolean result = userService.signInUser(view);

                    if (result) {
                        return "redirect:/profile/";
                    }
                }
            }
        } catch (IOException | NoSuchEntityException | WrongPasswordException e) {

        }
        return "redirect:/social_network_bad";
    }

    private final static Type MAP_RESPONSE_TYPE = new TypeToken<Map<String, Object>>(){}.getType();

    private final Set<String> SERVICES_FIELDS_FOR_INDEX = new HashSet<>(
            Arrays.asList("id", "name", "description")
    );

    private final Set<String> COMPANIES_FIELDS_FOR_INDEX = new HashSet<>(
            Arrays.asList("id", "name")
    );

    private final Set<String> TESTIMONIALS_FIELDS_FOR_INDEX = new HashSet<>(
            Arrays.asList("id", "name", "text")
    );
    private final Set<String> POST_FIELDS_FOR_INDEX = new HashSet<>(Arrays.asList(
            "url", "title", "date", "short_description", "category_code", "category_name"
    ));
}
