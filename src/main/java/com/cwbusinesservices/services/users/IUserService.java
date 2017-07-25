package com.cwbusinesservices.services.users;

import com.cwbusinesservices.criteria.impl.UserCriteria;
import com.cwbusinesservices.exceptions.bad_request.WrongPasswordException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.conflict.EmailExistsException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.view.UserView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Andrii on 18.08.2016.
 */
public interface IUserService {

    UserEntity getById(int userId) throws NoSuchEntityException;

    Map<String, Object> getById(int userId, Set<String> fields) throws NoSuchEntityException;

    List<UserEntity> getList(UserCriteria criteria) throws NoSuchEntityException;

    List<Map<String, Object>> getList(int offset, int limit, Set<String> fields, String restrict) throws NoSuchEntityException, WrongRestrictionException;

    UserEntity getByEmail(String email) throws NoSuchEntityException;

    int create(UserView view) throws EmailExistsException, ServiceErrorException, ValidationException;

    boolean update(UserView view) throws NoSuchEntityException;

    boolean signInUser(UserView view) throws NoSuchEntityException, WrongPasswordException;

    boolean logoutUser(HttpServletRequest request, HttpServletResponse response);

    int count(String restrict) throws WrongRestrictionException;
}
