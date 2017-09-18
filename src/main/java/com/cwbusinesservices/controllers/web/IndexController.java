package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.criteria.impl.*;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongPasswordException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.entities.BlockEntity;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.pojo.enums.BlockCodesEnum;
import com.cwbusinesservices.pojo.enums.OrderDirectionEnum;
import com.cwbusinesservices.pojo.view.UserView;
import com.cwbusinesservices.services.blocks.IBlockService;
import com.cwbusinesservices.services.blog.IPostService;
import com.cwbusinesservices.services.carousel_images.ICarouselImageService;
import com.cwbusinesservices.services.company.ICompanyService;
import com.cwbusinesservices.services.industry.IIndustryService;
import com.cwbusinesservices.services.interests.IInterestsService;
import com.cwbusinesservices.services.service.IServiceService;
import com.cwbusinesservices.services.testimonial.ITestimonialService;
import com.cwbusinesservices.services.users.IUserService;
import com.cwbusinesservices.services.utils.Utils;
import com.cwbusinesservices.shedule.ReadDatabaseScheduler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Andrii on 27.07.2016.
 */
@Controller
public class IndexController {

    @Autowired
    private IInterestsService interestsService;

    @Autowired
    private IIndustryService industryService;

    @Autowired
    private IUserService userService;

    @Autowired
    private Utils utils;

    @Autowired
    private ICarouselImageService carouselImageService;

    @Autowired
    private ReadDatabaseScheduler scheduler;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String indexPage(
            Model model
    ){
        model.addAttribute("blocks", scheduler.BLOCKS);
        model.addAttribute("services", scheduler.SERVICES);
        model.addAttribute("companies", scheduler.COMPANIES);
        model.addAttribute("testimonials", scheduler.TESTIMONIALS);
        model.addAttribute("posts", scheduler.LAST_POSTS);

        try {
            model.addAttribute("carousel_images", carouselImageService.getList(new CarouselImageCriteria()));
        } catch (BaseException e) { }

        return "index/index";
    }

    @RequestMapping(value = "/contact_us", method = RequestMethod.GET)
    public String contactUs() {
        return "index/contact_us";
    }

    @RequestMapping(value = "/robots.txt", method = RequestMethod.GET, produces = "text/plain")
    public @ResponseBody String robotsTxt() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource("robots.txt").getFile();
        File file = new File(path);


        String line;
        StringBuilder result = new StringBuilder();

        FileReader fileReader = null;
        BufferedReader reader = null;

        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);

            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) { }
            }

            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e) { }
            }
        }

        return result.toString();
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String signUp(Principal principal, Model model) {
        if (principal != null) {
            return "redirect:/requests/create";
        }

        try {
            IndustryCriteria industryCriteria = new IndustryCriteria();
            industryCriteria.setOrder_by("position");

            InterestCriteria interestCriteria = new InterestCriteria();
            interestCriteria.setOrder_by("position");

            model.addAttribute("industries", industryService.getList(industryCriteria));
            model.addAttribute("interests", interestsService.getList(interestCriteria));
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
                            "&access_token=" + appAccessToken()
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

    private String appAccessToken() throws IOException, NoSuchEntityException {
        Gson gson = new Gson();

        String userResponse = utils.readResponseBody(
                "https://graph.facebook.com/oauth/access_token?client_id=" + facebookAppId+"&client_secret=" + facebookAppSecret + "&grant_type=client_credentials"
        );

        Map<String, Object> map = gson.fromJson(userResponse, MAP_RESPONSE_TYPE);

        if (map.get("access_token") != null) {
            return map.get("access_token").toString();
        }

        throw new NoSuchEntityException("facebook app");
    }

    @Value("${facebook.app_id}")
    private String facebookAppId;

    @Value("${facebook.app_secret}")
    private String facebookAppSecret;

    private final static Type MAP_RESPONSE_TYPE = new TypeToken<Map<String, Object>>(){}.getType();

    private final Set<String> SERVICES_FIELDS_FOR_INDEX = new HashSet<>(
            Arrays.asList("id", "name", "short_description")
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
