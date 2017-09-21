package com.cwbusinesservices.services.files;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.StorageException;
import com.cwbusinesservices.pojo.enums.FileEntityTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Andrii on 28.07.2017.
 */
public interface IFileService {
    Boolean uploadFile(int id, MultipartFile file, FileEntityTypeEnum type) throws BaseException;
    void getFile(int id, HttpServletResponse response, FileEntityTypeEnum type) throws NoSuchEntityException, ServiceErrorException, StorageException;
    Boolean deleteFile(int id, FileEntityTypeEnum type) throws BaseException;
    Boolean hasFile(int id, FileEntityTypeEnum type) throws BaseException;
}
