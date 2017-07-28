package com.cwbusinesservices.services;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.pojo.entities.GetableById;
import com.cwbusinesservices.pojo.entities.IHasImage;

import java.io.Serializable;

/**
 * Created by Andrii on 28.07.2017.
 */
public abstract class ImageWorkBaseService<E extends GetableById<I>&IHasImage,V extends GetableById<I>&IHasImage,I extends Serializable> extends BaseService<E,V,I> implements IImageWork<I>{
    public ImageWorkBaseService(Class<E> entityClass){
        super(entityClass);
    }

    @Override
    public boolean changeImgStatus(I id, boolean status) throws BaseException {
        IHasImage entity = (IHasImage)getById(id);
        entity.setHasImage(status);
        return save((E)entity);
    }

    @Override
    public boolean hasImg(I id) throws BaseException {
        E entity = getById(id);
        return entity.isHasImage();
    }
}
