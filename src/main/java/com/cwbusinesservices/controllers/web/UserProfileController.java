package com.cwbusinesservices.controllers.web;

import com.cwbusinesservices.exceptions.bad_request.WrongPasswordException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.pojo.view.UserView;
import com.cwbusinesservices.services.users.IUserService;
import com.cwbusinesservices.services.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Oleh on 05.08.2017.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private IUserService userService;

    @Autowired
    private Utils utils;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String profile() {
        return "profile/profile";
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/social_network_bad", method = RequestMethod.GET)
    public String socialNetworkBad() {
        return "profile/can_not_login_sn";
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/facebook_success", method = RequestMethod.GET)
    public String faceBookSuccess() {
        return "profile/facebook_success";
    }

    @PreAuthorize("isAnonymous()")
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
        return "redirect:/profile/social_network_bad";
    }

    private final static Type MAP_RESPONSE_TYPE = new TypeToken<Map<String, Object>>(){}.getType();
}
