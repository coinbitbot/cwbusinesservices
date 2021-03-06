package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.pojo.view.CompanyView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 26.07.2017.
 */
@Component
public class CompanyMerger implements Merger<CompanyEntity,CompanyView>{

    @Autowired
    private Utils utils;

    @Autowired
    private FileMerger fileMerger;

    @Override
    public void merge(CompanyEntity entity, CompanyView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (view.getActive()!=null) entity.setActive(view.getActive());
        else view.setActive(entity.isActive());
        fileMerger.merge(view, entity);
        if (utils.notEmpty(view.getText())) entity.setText(view.getText());
        else view.setText(entity.getText());
        if (utils.notEmpty(view.getName())) entity.setName(view.getName());
        else view.setName(entity.getName());
    }
}
