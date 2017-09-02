package com.cwbusinesservices.mergers;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.EmployeeEntity;
import com.cwbusinesservices.pojo.view.EmployeeView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 29.08.2017.
 */
@Component
public class EmployeeMerger implements Merger<EmployeeEntity,EmployeeView>{

    @Autowired
    private Utils utils;

    @Autowired
    private FileMerger fileMerger;

    @Override
    public void merge(EmployeeEntity entity, EmployeeView view) throws BaseException {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (utils.notEmpty(view.getName())) entity.setName(view.getName());
        else view.setName(entity.getName());

        if (utils.notEmpty(view.getPosition())) entity.setPosition(view.getPosition());
        else view.setPosition(entity.getPosition());

        if (utils.notEmpty(view.getEmail())) entity.setEmail(view.getEmail());
        else view.setEmail(entity.getEmail());

        if (utils.notEmpty(view.getPhone())) entity.setPhone(view.getPhone());
        else view.setPhone(entity.getPhone());

        if (utils.notEmpty(view.getDescription())) entity.setDescription(view.getDescription());
        else view.setDescription(entity.getDescription());

        fileMerger.merge(view, entity);
    }
}
