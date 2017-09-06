package com.cwbusinesservices.services.interests;

import com.cwbusinesservices.criteria.impl.InterestCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.InterestEntity;
import com.cwbusinesservices.pojo.view.InterestView;
import com.cwbusinesservices.services.BaseService;

/**
 * Created by Andrii on 29.07.2017.
 */
public abstract class IInterestsService extends BaseService<InterestEntity,InterestView,Integer> {
    public IInterestsService(){ super(InterestEntity.class);}

    public abstract boolean swap(InterestCriteria criteria) throws BaseException;
}
