package com.cwbusinesservices.services.images;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.StorageException;
import com.cwbusinesservices.pojo.enums.ImageEntityTypeEnum;
import com.cwbusinesservices.services.IImageWork;
import com.cwbusinesservices.services.company.ICompanyService;
import com.cwbusinesservices.services.service.IServiceService;
import com.cwbusinesservices.storage.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Andrii on 28.07.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class IImageServiceImpl implements IImagesService{

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IServiceService serviceService;

    @Autowired
    private IStorageService storageService;

    @Override
    public Boolean uploadFile(int id, MultipartFile file, ImageEntityTypeEnum type) throws BaseException {
        boolean res = storageService.uploadFile(id, file, type);
        if (res) {
            res =changeFileStatus(id, type, true);
            if (!res)
                storageService.deleteFile(id,type);
        }
        return res;
    }

    private boolean changeFileStatus(int id, ImageEntityTypeEnum type, boolean status) throws BaseException {
        IImageWork<Integer> imageWork = getImageService(type);
        return imageWork.changeImgStatus(id,status);
    }

    private IImageWork<Integer> getImageService(ImageEntityTypeEnum type){
        switch (type){
            case COMPANY:return companyService;
            case SERVICE:return serviceService;
        }
        return null;
    }

    @Override
    public void getFile(int id, HttpServletResponse response, ImageEntityTypeEnum type) throws NoSuchEntityException, ServiceErrorException, StorageException {
        storageService.getFile(id, response, type);
    }

    @Override
    public Boolean deleteFile(int id, ImageEntityTypeEnum type) throws BaseException {
        boolean res = changeFileStatus(id, type, false);
        if (res)
            return storageService.deleteFile(id,type);
        return res;
    }

    @Override
    public Boolean hasFile(int id, ImageEntityTypeEnum type) throws BaseException {
        IImageWork<Integer> imageWork = getImageService(type);
        return imageWork.hasImg(id);
    }
}
