package com.cwbusinesservices.tests.services;

import com.cwbusinesservices.exceptions.conflict.EmailExistsException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.pojo.view.UserView;
import com.cwbusinesservices.services.users.IUserService;
import com.cwbusinesservices.services.users.UserValidateServiceImpl;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import javax.validation.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Andrii on 25.07.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserValidateServiceImplTest {

    @Mock
    private SessionUtils sessionUtils;

    @Mock
    private IUserService userService;

    @Mock
    private Validator validator;

    private Validator localValidator;

    @InjectMocks
    private UserValidateServiceImpl  userValidateService;

    @Before
    public void init() {

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.localValidator = vf.getValidator();

    }

    @Test
    public void doesNotValidForCreateEmpty(){
        UserEntity view = new UserEntity();
        Set<ConstraintViolation<UserEntity>> violations = localValidator.validate(view);
        assertTrue(violations.size()>0);
    }

    @Test
    public void validEntity(){
        UserEntity user = new UserEntity();
        user.setEmail("test@test.com");
        user.setPassword("testtest");
        Set<ConstraintViolation<UserEntity>> violations = localValidator.validate(user);
        assertTrue(violations.size()==0);
    }

    @Test
    public void notValidPassword(){
        UserEntity user = new UserEntity();
        user.setPassword("");
        Set<ConstraintViolation<UserEntity>> violations = localValidator.validateProperty(
                user,
                "password"
        );
        assertTrue(violations.size()==1);
    }

    @Test
    public void notValidEmail(){
        UserEntity user = new UserEntity();
        user.setEmail("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Set<ConstraintViolation<UserEntity>> violations = localValidator.validateProperty(
                user,
                "email"
        );
        assertTrue(violations.size()==2);
    }

    @Test(expected = EmailExistsException.class)
    public void emailExist() throws NoSuchEntityException, ValidationException, EmailExistsException, ServiceErrorException {
        UserEntity user = new UserEntity();
        user.setEmail("test@test.com");
        when(validator.validate(user)).then(new Answer<Set<ConstraintViolation<UserEntity>>>(){
            @Override
            public Set<ConstraintViolation<UserEntity>> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new HashSet<ConstraintViolation<UserEntity>>();
            }
        });
/*        when(userService.getByEmail("test@test,com")).then(new Answer<UserEntity>() {
            @Override
            public UserEntity answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new UserEntity();
            }
        });*/
        userValidateService.validForCreate(user);
    }

    @Test(expected = ServiceErrorException.class)
    public void notValidIdForCreation() throws ValidationException, EmailExistsException, ServiceErrorException {
        UserEntity user = new UserEntity();
        user.setId(1);
        userValidateService.validForCreate(user);
    }

}