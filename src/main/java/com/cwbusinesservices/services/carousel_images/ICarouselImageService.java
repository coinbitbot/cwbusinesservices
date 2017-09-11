package com.cwbusinesservices.services.carousel_images;

import com.cwbusinesservices.pojo.entities.CarouselImageEntity;
import com.cwbusinesservices.pojo.entities.EmployeeEntity;
import com.cwbusinesservices.pojo.enums.FileEntityTypeEnum;
import com.cwbusinesservices.pojo.view.CarouselImageView;
import com.cwbusinesservices.pojo.view.EmployeeView;
import com.cwbusinesservices.services.FileWorkBaseService;

/**
 * Created by Andrii on 29.08.2017.
 */
public abstract class ICarouselImageService
        extends FileWorkBaseService<CarouselImageEntity, CarouselImageView, Integer> {
    public ICarouselImageService(){super(CarouselImageEntity.class, FileEntityTypeEnum.CAROUSEL_IMAGE);}
}
