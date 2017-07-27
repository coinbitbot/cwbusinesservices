package com.cwbusinesservices.storage;

import org.springframework.web.multipart.MultipartFile;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.StorageException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andrii on 05.10.2016.
 */
public interface IStorageService {
    boolean uploadRequestFile(int id, MultipartFile file) throws NoSuchEntityException, ServiceErrorException;

    void getRequestFile(int id, HttpServletResponse response) throws NoSuchEntityException, StorageException;

    boolean requestHasFile(int id) throws NoSuchEntityException;

    boolean uploadServiceFile(int id, MultipartFile file) throws NoSuchEntityException, ServiceErrorException;

    void getServiceFile(int id, HttpServletResponse response) throws NoSuchEntityException, ServiceErrorException, StorageException;

    boolean serviceHasFile(int id) throws NoSuchEntityException;
}
