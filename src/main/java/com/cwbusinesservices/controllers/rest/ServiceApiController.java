package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.ServiceEntity;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.view.ServiceView;
import com.cwbusinesservices.storage.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Andrii on 27.07.2017.
 */
@Controller
@RequestMapping(value = "/api/service")
public class ServiceApiController extends BaseApiController<ServiceEntity,ServiceView,Integer>{

    @Autowired
    private IStorageService storageService;

    @RequestMapping(value = "/storage", method = RequestMethod.POST)
    public @ResponseBody
    Response<Boolean> uploadServiceFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") int id
    ) throws BaseException {
        return responseFactory.get(storageService.uploadServiceFile(id, file));
    }

}
