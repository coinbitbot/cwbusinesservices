package com.cwbusinesservices.services.industry;

import com.cwbusinesservices.pojo.entities.IndustryEntity;
import com.cwbusinesservices.pojo.view.IndustryView;
import com.cwbusinesservices.services.BaseService;

/**
 * Created by Andrii on 29.07.2017.
 */
public abstract class IIndustryService extends BaseService<IndustryEntity,IndustryView,Integer> {
    public IIndustryService(){ super(IndustryEntity.class);}
}
