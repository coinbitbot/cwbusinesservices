package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.enums.ImageEtityTypeEnum;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.storage.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Andrii on 28.07.2017.
 */
@Controller
@RequestMapping(value = "/api/image")
public class ImagesApiController {

    @Autowired
    ResponseFactory responseFactory;
    @Autowired
    private IStorageService storageService;

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public @ResponseBody
    Response<Boolean> uploadServiceFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") int id,
            @RequestParam("type")ImageEtityTypeEnum type
            ) throws BaseException {
        return responseFactory.get(storageService.uploadFile(id, file,type));
    }
}
