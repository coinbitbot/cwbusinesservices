package com.cwbusinesservices.mergers;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.CarouselImageEntity;
import com.cwbusinesservices.pojo.entities.EmployeeEntity;
import com.cwbusinesservices.pojo.view.CarouselImageView;
import com.cwbusinesservices.pojo.view.EmployeeView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 29.08.2017.
 */
@Component
public class CarouselImageMerger implements Merger<CarouselImageEntity, CarouselImageView>{

    @Autowired
    private Utils utils;

    @Autowired
    private FileMerger fileMerger;

    @Override
    public void merge(CarouselImageEntity entity, CarouselImageView view) throws BaseException {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());

        if (view.getPosition() != null) entity.setPosition(view.getPosition());
        else view.setPosition(entity.getPosition());

        if (utils.notEmpty(view.getDescription())) entity.setDescription(view.getDescription());
        else view.setDescription(entity.getDescription());

        fileMerger.merge(view, entity);
    }
}
