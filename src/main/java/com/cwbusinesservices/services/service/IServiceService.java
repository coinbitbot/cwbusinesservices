package com.cwbusinesservices.services.service;

import com.cwbusinesservices.pojo.entities.ServiceEntity;
import com.cwbusinesservices.pojo.view.ServiceView;
import com.cwbusinesservices.services.BaseService;

/**
 * Created by Andrii on 27.07.2017.
 */
public abstract class IServiceService extends BaseService<ServiceEntity,ServiceView,Integer>{
    public IServiceService(){
        super(ServiceEntity.class);
    }
}
