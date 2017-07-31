package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import com.cwbusinesservices.pojo.view.EmailTemplateView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 31.07.2017.
 */
@Component
public class EmailTemplateMerger implements Merger<EmailTemplateEntity,EmailTemplateView>{

    @Autowired
    private Utils utils;
    @Override
    public void merge(EmailTemplateEntity entity, EmailTemplateView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (utils.notEmpty(view.getText())) entity.setText(view.getText());
        else view.setText(entity.getText());
        if (utils.notEmpty(view.getSubject())) entity.setSubject(view.getSubject());
        else view.setSubject(entity.getSubject());
        if(view.getCode()!=null) entity.setCode(view.getCode());
        else view.setCode(entity.getCode());
    }
}
