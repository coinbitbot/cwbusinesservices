package com.cwbusinesservices.services.users;

import com.cwbusinesservices.exceptions.conflict.EmailExistsException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.pojo.enums.RolesEnum;
import com.cwbusinesservices.pojo.view.UserView;
import com.cwbusinesservices.services.utils.SessionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Andrii on 10.09.2016.
 */
@Service
public class UserValidateServiceImpl implements IUserValidateService {

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private Validator validator;

    @Autowired
    private IUserService userService;

    @Override
    public void validForCreate(UserView user) throws ServiceErrorException, ValidationException, EmailExistsException {

        try{
            userService.getByEmail(user.getEmail());
            throw new EmailExistsException();
        } catch (NoSuchEntityException e) {
            if (user.getId()>0)
                throw new ServiceErrorException("User should be updated");
            Set<ConstraintViolation<UserView>> violations = validator.validate(user);
            if(violations != null && !violations.isEmpty()) {
                throw new ValidationException(UserEntity.class.getName(), violations);
            }
            if (!sessionUtils.isAuthorized()){
                if (user.getRole()==null||!user.getRole().equals(RolesEnum.user)){
                    throw new ServiceErrorException();
                }
            }else if (!sessionUtils.isUserWithRole(RolesEnum.admin)){
                throw new ServiceErrorException();
            }
        }
    }
}
