package com.cwbusinesservices.services;

import com.cwbusinesservices.criteria.impl.UserCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongPasswordException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.pojo.view.UserView;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 25.07.2017.
 */
public interface IService<E,V,C> {
    E getById(int id) throws BaseException;
    Map<String, Object> getById(int id, Set<String> fields) throws BaseException;
    List<E> getList(C criteria) throws BaseException;
    List<Map<String, Object>> getList(int offset, int limit, Set<String> fields, String restrict) throws BaseException;
    int create(V view) throws BaseException;
    boolean update(V view) throws BaseException;
    int count(String restrict) throws WrongRestrictionException;
    boolean delete(int id) throws BaseException;
}
