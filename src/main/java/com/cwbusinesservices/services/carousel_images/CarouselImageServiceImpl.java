package com.cwbusinesservices.services.carousel_images;

import com.cwbusinesservices.convertors.CarouselImageConverter;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.CarouselImageCriteria;
import com.cwbusinesservices.criteria.impl.EmployeeCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.CarouselImageEntity;
import com.cwbusinesservices.pojo.entities.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 29.08.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class CarouselImageServiceImpl extends ICarouselImageService{

    @Autowired
    private CarouselImageConverter converter;

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<CarouselImageEntity> criteria = new CarouselImageCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<CarouselImageEntity> criteria = new CarouselImageCriteria(restrict);
        return count(criteria);
    }
}
