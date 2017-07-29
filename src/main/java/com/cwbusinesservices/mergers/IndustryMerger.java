package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.IndustryEntity;
import com.cwbusinesservices.pojo.view.IndustryView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 29.07.2017.
 */
@Component
public class IndustryMerger implements Merger<IndustryEntity,IndustryView>{

    @Autowired
    private Utils utils;

    @Override
    public void merge(IndustryEntity entity, IndustryView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (utils.notEmpty(view.getName())) entity.setName(view.getName());
        else view.setName(entity.getName());
    }
}
