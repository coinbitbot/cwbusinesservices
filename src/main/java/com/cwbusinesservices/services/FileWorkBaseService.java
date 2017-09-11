package com.cwbusinesservices.services;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.enums.FileEntityTypeEnum;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;
import com.cwbusinesservices.pojo.helpers.IHasImage;
import com.cwbusinesservices.storage.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by Andrii on 29.07.2017.
 */
public abstract class FileWorkBaseService <E extends GetableById<I> &IHasFile,V extends GetableById<I>&IHasFile,I extends Serializable> extends BaseService<E,V,I> implements IFileWork<I>{

    @Autowired
    private IStorageService storageService;

    private final FileEntityTypeEnum fileType;

    public FileWorkBaseService(Class<E> entityClass, FileEntityTypeEnum fileType){
        super(entityClass);
        this.fileType = fileType;
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

    @Override
    public boolean delete(I id) throws BaseException {
        boolean result = super.delete(id);

        if (result) {
            try {
                storageService.deleteFile((Integer) id, fileType);
            } catch (Exception e) {
                //TODO: log fail file delete operation
            }
        }

        return result;
    }
}
