package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.ServiceEntity;
import com.cwbusinesservices.pojo.view.ServiceView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 27.07.2017.
 */
@Component
public class ServiceMerger implements Merger<ServiceEntity,ServiceView>{

    @Autowired
    private Utils utils;

    @Autowired
    private ImageMerger imageMerger;

    @Override
    public void merge(ServiceEntity entity, ServiceView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (view.getActive()!=null) entity.setActive(view.getActive());
        else view.setActive(entity.isActive());
        imageMerger.merge(view, entity);
        if (utils.notEmpty(view.getDescription())) entity.setDescription(view.getDescription());
        else view.setDescription(entity.getDescription());
        if (utils.notEmpty(view.getName())) entity.setName(view.getName());
        else view.setName(entity.getName());
    }
}
