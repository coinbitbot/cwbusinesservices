package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.InterestEntity;
import com.cwbusinesservices.pojo.view.InterestView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 29.07.2017.
 */
@Component
public class InterestMerger implements Merger<InterestEntity,InterestView>{

    @Autowired
    private Utils utils;

    @Override
    public void merge(InterestEntity entity, InterestView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (utils.notEmpty(view.getName())) entity.setName(view.getName());
        else view.setName(entity.getName());
    }
}
