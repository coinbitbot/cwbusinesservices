package com.cwbusinesservices.services.infopages;

import com.cwbusinesservices.criteria.impl.InfoPageCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.entities.InfoPageEntity;
import com.cwbusinesservices.pojo.view.InfoPageView;
import com.cwbusinesservices.services.IService;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 25.07.2017.
 */
public interface IInfoPageService extends IService<InfoPageEntity,InfoPageView,InfoPageCriteria> {

    InfoPageEntity getByUrl(String url) throws BaseException;
    Map<String,Object> getByUrl(String url,Set<String> fields) throws BaseException;

}
