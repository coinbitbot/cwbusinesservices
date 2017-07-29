package com.cwbusinesservices.services.testimonial;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.conflict.EntityValidateException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.pojo.entities.TestimonialEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
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
public class ITestimonialValidationServiceImpl extends BaseValidator<TestimonialEntity,Integer> implements ITestimonialValidationService{

    public ITestimonialValidationServiceImpl(){
        super(PermissionsEnum.CREATE_TESTIMONIAL,PermissionsEnum.EDIT_TESTIMONIAL,PermissionsEnum.DELETE_TESTIMONIAL,TestimonialEntity.class);
    }

    @Autowired
    private SessionUtils sessionUtils;

    @Override
    public void validForView(TestimonialEntity entity) throws BaseException {
        if (entity.isActive())
            return;
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_TESTIMONIAL))
            throw new ForbiddenException();
    }

}
