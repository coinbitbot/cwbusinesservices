package com.cwbusinesservices.storage;

import com.cwbusinesservices.pojo.enums.ImageEntityTypeEnum;
import org.springframework.web.multipart.MultipartFile;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.StorageException;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Andrii on 05.10.2016.
 */
public interface IStorageService {

    Boolean uploadFile(int id, MultipartFile file, ImageEntityTypeEnum type) throws ServiceErrorException;

    Boolean deleteFile(int id, ImageEntityTypeEnum type) throws ServiceErrorException;

    void getFile(int id, HttpServletResponse response, ImageEntityTypeEnum type) throws NoSuchEntityException, ServiceErrorException, StorageException;

    Boolean hasFile(int id, ImageEntityTypeEnum type);
}
