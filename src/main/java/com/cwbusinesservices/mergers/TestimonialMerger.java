package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.TestimonialEntity;
import com.cwbusinesservices.pojo.view.TestimonialView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 27.07.2017.
 */
@Component
public class TestimonialMerger extends Merger<TestimonialEntity,TestimonialView>{

    @Autowired
    private Utils utils;

    @Override
    public void merge(TestimonialEntity entity, TestimonialView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (view.getActive()!=null) entity.setActive(view.getActive());
        else view.setActive(entity.isActive());
        if(view.getPosition()!=null) entity.setPosition(view.getPosition());
        else view.setPosition(entity.getPosition());
        if (utils.notEmpty(view.getText())) entity.setText(view.getText());
        else view.setText(entity.getText());
        if (utils.notEmpty(view.getName())) entity.setName(view.getName());
        else view.setName(entity.getName());
    }
}
