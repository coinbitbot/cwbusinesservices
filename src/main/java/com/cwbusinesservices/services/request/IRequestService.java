package com.cwbusinesservices.services.request;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import com.cwbusinesservices.pojo.view.RequestView;
import com.cwbusinesservices.services.FileWorkBaseService;

/**
 * Created by Andrii on 29.07.2017.
 */
public abstract class IRequestService extends FileWorkBaseService<RequestEntity,RequestView,Integer>{
    public IRequestService(){ super(RequestEntity.class);}

    public abstract Boolean changeStatus(RequestView view) throws BaseException;
}
