package com.cwbusinesservices.services.blog;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.BlogCategoryEntity;
import com.cwbusinesservices.pojo.enums.BlockCodesEnum;
import com.cwbusinesservices.pojo.view.BlogCategoryView;
import com.cwbusinesservices.services.BaseService;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 02.08.2017.
 */
public abstract class IBlogCategoryService extends BaseService<BlogCategoryEntity,BlogCategoryView,Integer>{
    public IBlogCategoryService(){
        super(BlogCategoryEntity.class);
    }

    public abstract Map<String,Object> getByCode(String code, Set<String> fields) throws BaseException;
    public abstract BlogCategoryEntity getByCode(String code) throws BaseException;
}
