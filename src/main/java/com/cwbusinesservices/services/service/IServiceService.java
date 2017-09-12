package com.cwbusinesservices.services.service;

import com.cwbusinesservices.criteria.impl.ServiceCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.ServiceEntity;
import com.cwbusinesservices.pojo.enums.FileEntityTypeEnum;
import com.cwbusinesservices.pojo.view.ServiceView;
import com.cwbusinesservices.services.FileWorkBaseService;

/**
 * Created by Andrii on 27.07.2017.
 */
public abstract class IServiceService extends FileWorkBaseService<ServiceEntity,ServiceView,Integer> {
    public IServiceService(){
        super(ServiceEntity.class, FileEntityTypeEnum.SERVICE);
    }

    public abstract boolean swap(ServiceCriteria criteria) throws BaseException;
}
