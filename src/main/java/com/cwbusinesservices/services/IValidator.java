package com.cwbusinesservices.services;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.view.UserView;

import java.util.List;

/**
 * Created by Andrii on 25.07.2017.
 */
public interface IValidator<E> {
    void validForCreate(E entity) throws BaseException;
    void validForUpdate(E entity) throws BaseException;
    void validForView(E entity) throws BaseException;
    void validForView(List<E> entities) throws BaseException;
    void validForDelete(E entity) throws BaseException;
}
