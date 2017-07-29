package com.cwbusinesservices.services.request;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.RequestCommentEntity;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import com.cwbusinesservices.pojo.view.RequestView;
import com.cwbusinesservices.services.IValidator;

/**
 * Created by Andrii on 29.07.2017.
 */
public interface IRequestValidationService extends IValidator<RequestEntity>{
    void validForStatusChange(RequestView view, RequestEntity entity) throws BaseException;
}
