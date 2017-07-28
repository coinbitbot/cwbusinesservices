package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.InfoPageEntity;
import com.cwbusinesservices.pojo.view.InfoPageView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 25.07.2017.
 */
@Component
public class InfoPageMerger implements Merger<InfoPageEntity,InfoPageView>{

    @Autowired
    private Utils utils;

    @Override
    public void merge(InfoPageEntity entity, InfoPageView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (utils.notEmpty(view.getUrl())) entity.setUrl(view.getUrl());
        else view.setUrl(entity.getUrl());
        if (utils.notEmpty(view.getHeader())) entity.setHeader(view.getHeader());
        else view.setHeader(entity.getHeader());
        if (utils.notEmpty(view.getSub_header())) entity.setSubHeader(view.getSub_header());
        else view.setSub_header(entity.getSubHeader());
        if (utils.notEmpty(view.getText())) entity.setText(view.getText());
        else view.setText(entity.getText());
        if (view.getActive()!=null) entity.setActive(view.getActive());
        else view.setActive(entity.isActive());
        if (utils.notEmpty(view.getMeta_description())) entity.setMetaDescription(view.getMeta_description());
        else view.setMeta_description(entity.getMetaDescription());
        if (utils.notEmpty(view.getMeta_keywords())) entity.setMetaKeywords(view.getMeta_keywords());
        else view.setMeta_keywords(entity.getMetaKeywords());
        if(utils.notEmpty(view.getMeta_title())) entity.setMetaTitle(view.getMeta_title());
        else view.setMeta_title(entity.getMetaTitle());
    }
}
