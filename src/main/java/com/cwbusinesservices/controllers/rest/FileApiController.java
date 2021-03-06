package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.enums.FileEntityTypeEnum;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.response.ResponseFactory;
import com.cwbusinesservices.services.files.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrii on 28.07.2017.
 */
@Controller
@RequestMapping(value = "/api/file")
public class FileApiController {

    @Autowired
    private ResponseFactory responseFactory;
    @Autowired
    private IFileService imageService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody
    Response<Boolean> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") int id,
            @RequestParam("type") FileEntityTypeEnum type
    ) throws BaseException {
        return responseFactory.get(imageService.uploadFile(id, file, type));
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public @ResponseBody
    Response<Boolean> deleteFile(
            @RequestParam("id") int id,
            @RequestParam("type") FileEntityTypeEnum type
    ) throws BaseException {
        return responseFactory.get(imageService.deleteFile(id, type));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("id") int id,
            @RequestParam("type") FileEntityTypeEnum type,
            HttpServletResponse response
    ) throws BaseException {
        imageService.getFile(id, response, type);
    }

    @RequestMapping(value = "/has_file/{id}", method = RequestMethod.GET)
    public void hasFile(
            @PathVariable("id") int id,
            @RequestParam("type") FileEntityTypeEnum type
    ) throws BaseException {
        imageService.hasFile(id, type);
    }

    @RequestMapping(value = "/images", method = RequestMethod.POST)
    public @ResponseBody
    Response<Boolean> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "name", required = false) String name
    ) throws BaseException {
        return responseFactory.get(imageService.uploadImage(file, name));
    }

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public @ResponseBody
    Response<List<String>> getImagesData() throws BaseException {
        return responseFactory.get(imageService.getImagesData());
    }

    @RequestMapping(value = "/images/{number}", method = RequestMethod.DELETE)
    public @ResponseBody
    Response<Boolean> deleteImage(
            @PathVariable("number") int number
    ) throws BaseException {
        return responseFactory.get(imageService.deleteImage(number));
    }

    @RequestMapping(value = "/images/{name}", method = RequestMethod.GET)
    public void getImage(
            @PathVariable("name") String name,
            HttpServletResponse response
    ) throws BaseException {
        imageService.getImage(name, response);
    }

    @RequestMapping(value = "/images/count", method = RequestMethod.GET)
    public @ResponseBody
    Response<Integer> countImages(
    ) throws BaseException {
        return responseFactory.get(imageService.countImages());
    }
}
