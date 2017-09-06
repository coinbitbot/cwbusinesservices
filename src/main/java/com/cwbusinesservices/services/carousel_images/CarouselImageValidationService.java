package com.cwbusinesservices.services.carousel_images;

import com.cwbusinesservices.pojo.entities.CarouselImageEntity;
import com.cwbusinesservices.pojo.entities.EmployeeEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import com.cwbusinesservices.services.employees.IEmployeeValidationService;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 29.08.2017.
 */
@Service
public class CarouselImageValidationService extends BaseValidator<CarouselImageEntity,Integer> implements ICarouselImageValidationService{
    public CarouselImageValidationService(){
        super(PermissionsEnum.CREATE_CAROUSEL_IMAGE, PermissionsEnum.EDIT_CAROUSEL_IMAGE,PermissionsEnum.DELETE_CAROUSEL_IMAGE, CarouselImageEntity.class);
    }
}
