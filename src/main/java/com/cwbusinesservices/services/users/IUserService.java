package com.cwbusinesservices.services.users;

import com.cwbusinesservices.exceptions.bad_request.WrongPasswordException;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.view.UserView;
import com.cwbusinesservices.services.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Andrii on 18.08.2016.
 */
public abstract class IUserService extends BaseService<UserEntity, UserView, Integer> {

    public IUserService() {
        super(UserEntity.class);
    }

    public abstract UserEntity getByEmail(String email) throws NoSuchEntityException;

    public abstract boolean signInUser(UserView view) throws NoSuchEntityException, WrongPasswordException;

    public abstract boolean logoutUser(HttpServletRequest request, HttpServletResponse response);
}
