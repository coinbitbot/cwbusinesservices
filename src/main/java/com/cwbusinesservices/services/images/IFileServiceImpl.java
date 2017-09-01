package com.cwbusinesservices.services.images;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.StorageException;
import com.cwbusinesservices.pojo.enums.FileEntityTypeEnum;
import com.cwbusinesservices.services.IFileWork;
import com.cwbusinesservices.services.blog.IPostService;
import com.cwbusinesservices.services.company.ICompanyService;
import com.cwbusinesservices.services.employees.IEmployeeService;
import com.cwbusinesservices.services.request.IRequestService;
import com.cwbusinesservices.services.requestcomment.IRequestCommentService;
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
@Transactional(propagation= Propagation.REQUIRED, rollbackFor = BaseException.class)
public class IFileServiceImpl implements IFileService {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IServiceService serviceService;

    @Autowired
    private IStorageService storageService;

    @Autowired
    private IRequestService requestService;

    @Autowired
    private IRequestCommentService requestCommentService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IEmployeeService employeeService;

    @Override
    public Boolean uploadFile(int id, MultipartFile file, FileEntityTypeEnum type) throws BaseException {
        boolean res = storageService.uploadFile(id, file, type);
        if (res) {
            res =changeFileStatus(id, type, true);
            if (!res)
                storageService.deleteFile(id,type);
        }
        return res;
    }

    private boolean changeFileStatus(int id, FileEntityTypeEnum type, boolean status) throws BaseException {
        IFileWork<Integer> fileWork = getFileService(type);
        return fileWork.changeFileStatus(id,status);
    }

    private IFileWork<Integer> getFileService(FileEntityTypeEnum type){
        switch (type){
            case COMPANY:return companyService;
            case SERVICE:return serviceService;
            case REQUEST:return requestService;
            case REQUEST_COMMENT: return requestCommentService;
            case POST: return postService;
            case EMPLOYEE:return employeeService;
        }
        return null;
    }

    @Override
    public void getFile(int id, HttpServletResponse response, FileEntityTypeEnum type) throws NoSuchEntityException, ServiceErrorException, StorageException {
        storageService.getFile(id, response, type);
    }

    @Override
    public Boolean deleteFile(int id, FileEntityTypeEnum type) throws BaseException {
        boolean res = changeFileStatus(id, type, false);
        if (res)
            return storageService.deleteFile(id,type);
        return res;
    }

    @Override
    public Boolean hasFile(int id, FileEntityTypeEnum type) throws BaseException {
        IFileWork<Integer> fileWork = getFileService(type);
        return fileWork.hasFile(id);
    }
}
