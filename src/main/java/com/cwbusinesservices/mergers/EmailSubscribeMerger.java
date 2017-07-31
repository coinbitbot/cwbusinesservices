package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.EmailSubscribeEntity;
import com.cwbusinesservices.pojo.view.EmailSubscribeView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 31.07.2017.
 */
@Component
public class EmailSubscribeMerger implements Merger<EmailSubscribeEntity,EmailSubscribeView>{

    @Autowired
    private Utils utils;


    @Override
    public void merge(EmailSubscribeEntity entity, EmailSubscribeView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (utils.notEmpty(view.getEmail())) entity.setEmail(view.getEmail());
        else view.setEmail(entity.getEmail());
    }
}
