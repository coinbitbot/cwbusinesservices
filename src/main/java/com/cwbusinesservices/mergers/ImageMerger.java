package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.IHasImage;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 28.07.2017.
 */
@Component
public class ImageMerger {
    /**
     *
     * @param view Entity view
     * @param entity Main entity
     */
    public void merge(IHasImage view, IHasImage entity){
        if(view.isHasImage()!=null) entity.setHasImage(view.isHasImage());
        else view.setHasImage(entity.isHasImage());
    }
}
