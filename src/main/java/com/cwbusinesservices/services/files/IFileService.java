package com.cwbusinesservices.services.files;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.StorageException;
import com.cwbusinesservices.pojo.enums.FileEntityTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrii on 28.07.2017.
 */
public interface IFileService {
    Boolean uploadFile(int id, MultipartFile file, FileEntityTypeEnum type) throws BaseException;
    void getFile(int id, HttpServletResponse response, FileEntityTypeEnum type) throws NoSuchEntityException, ServiceErrorException, StorageException;
    Boolean deleteFile(int id, FileEntityTypeEnum type) throws BaseException;
    Boolean hasFile(int id, FileEntityTypeEnum type) throws BaseException;

    Boolean uploadImage(MultipartFile file, String name) throws BaseException;

    /**
     *
     */
    List<String> getImagesData();
    void getImage(String name, HttpServletResponse response) throws NoSuchEntityException, ServiceErrorException, StorageException;
    Boolean deleteImage(int number) throws BaseException;
    int countImages();
}
