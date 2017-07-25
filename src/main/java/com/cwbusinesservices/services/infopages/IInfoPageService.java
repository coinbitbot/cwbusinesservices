package com.cwbusinesservices.services.infopages;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.InfoPageEntity;
import com.cwbusinesservices.pojo.view.InfoPageView;
import com.cwbusinesservices.services.BaseService;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 25.07.2017.
 */
public abstract class IInfoPageService extends BaseService<InfoPageEntity,InfoPageView,Integer> {

    public IInfoPageService(){
        super(InfoPageEntity.class);
    }

    public abstract InfoPageEntity getByUrl(String url) throws BaseException;
    public abstract Map<String,Object> getByUrl(String url,Set<String> fields) throws BaseException;

}
