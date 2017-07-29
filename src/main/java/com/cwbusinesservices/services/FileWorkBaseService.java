package com.cwbusinesservices.services;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;
import com.cwbusinesservices.pojo.helpers.IHasImage;

import java.io.Serializable;

/**
 * Created by Andrii on 29.07.2017.
 */
public abstract class FileWorkBaseService <E extends GetableById<I> &IHasFile,V extends GetableById<I>&IHasFile,I extends Serializable> extends BaseService<E,V,I> implements IFileWork<I>{
    public FileWorkBaseService(Class<E> entityClass){
        super(entityClass);
    }
    @Override
    public boolean changeFileStatus(I id, boolean status) throws BaseException{
        IHasFile entity = (IHasFile)getById(id);
        entity.setHasFile(status);
        return save((E)entity);
    }
    @Override
    public boolean hasFile(I id) throws BaseException{
        E entity = getById(id);
        return entity.isHasFile();
    }
}
