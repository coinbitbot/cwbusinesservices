package com.cwbusinesservices.storage;

import com.cwbusinesservices.pojo.enums.FileEntityTypeEnum;
import org.springframework.web.multipart.MultipartFile;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.StorageException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrii on 05.10.2016.
 */
public interface IStorageService {

    Boolean uploadFile(int id, MultipartFile file, FileEntityTypeEnum type) throws ServiceErrorException;

    Boolean deleteFile(int id, FileEntityTypeEnum type) throws ServiceErrorException;

    void getFile(int id, HttpServletResponse response, FileEntityTypeEnum type) throws NoSuchEntityException, ServiceErrorException, StorageException;

    Boolean hasFile(int id, FileEntityTypeEnum type);

    Boolean uploadImage(MultipartFile file, String name) throws ServiceErrorException;

    Boolean deleteImage(int number) throws ServiceErrorException;

    void getImage(String name, HttpServletResponse response) throws NoSuchEntityException, ServiceErrorException, StorageException;

    int countImages();

    List<String> getImagesData();
}
