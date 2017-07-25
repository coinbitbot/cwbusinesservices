package com.cwbusinesservices.services.users;

import com.cwbusinesservices.exceptions.conflict.EmailExistsException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.view.UserView;

/**
 * Created by Andrii on 10.09.2016.
 */
public interface IUserValidateService {

    void validForCreate(UserView user) throws ServiceErrorException, ValidationException, EmailExistsException;
}
