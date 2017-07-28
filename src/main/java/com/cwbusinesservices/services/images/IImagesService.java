package com.cwbusinesservices.services.images;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.StorageException;
import com.cwbusinesservices.pojo.enums.ImageEntityTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Andrii on 28.07.2017.
 */
public interface IImagesService {
    Boolean uploadFile(int id, MultipartFile file, ImageEntityTypeEnum type) throws BaseException;
    void getFile(int id, HttpServletResponse response, ImageEntityTypeEnum type) throws NoSuchEntityException, ServiceErrorException, StorageException;
    Boolean deleteFile(int id, ImageEntityTypeEnum type) throws BaseException;
    Boolean hasFile(int id, ImageEntityTypeEnum type) throws BaseException;
}
