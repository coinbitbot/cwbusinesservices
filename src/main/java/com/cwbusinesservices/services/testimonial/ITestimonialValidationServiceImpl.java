package com.cwbusinesservices.services.testimonial;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.TestimonialEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 27.07.2017.
 */
@Service
public class ITestimonialValidationServiceImpl implements ITestimonialValidationService{

    @Autowired
    private SessionUtils sessionUtils;
    @Autowired
    private Validator validator;

    @Override
    public void validForCreate(TestimonialEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.CREATE_TESTIMONIAL))
            throw new ForbiddenException();
        if (entity.getId()>0)
            throw new EntityValidateException("errors.EntityCreateException.id.create");
        Set<ConstraintViolation<TestimonialEntity>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(TestimonialEntity.class.getName(), violations);
        }
    }

    @Override
    public void validForUpdate(TestimonialEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_TESTIMONIAL))
            throw new ForbiddenException();
        if (entity.getId()==0)
            throw new EntityValidateException("errors.EntityCreateException.id.update");
        Set<ConstraintViolation<TestimonialEntity>> violations = validator.validate(entity);
        if(violations != null && !violations.isEmpty()) {
            throw new ValidationException(TestimonialEntity.class.getName(), violations);
        }
    }

    @Override
    public void validForView(TestimonialEntity entity) throws BaseException {
        if (entity.isActive())
            return;
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_TESTIMONIAL))
            throw new ForbiddenException();
    }

    @Override
    public void validForView(List<TestimonialEntity> entities) throws BaseException {
        for (TestimonialEntity entity:entities)
            validForView(entity);
    }

    @Override
    public void validForDelete(TestimonialEntity entity) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.DELETE_TESTIMONIAL))
            throw new ForbiddenException();
    }
}
