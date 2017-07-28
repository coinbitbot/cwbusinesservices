package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.IHasImage;

/**
 * Created by Andrii on 28.07.2017.
 */
public abstract class ImageMerger {
    /**
     *
     * @param view Entity view
     * @param entity Main entity
     */
    public void mergeImages(IHasImage view,IHasImage entity){
        if(view.isHasImage()!=null) entity.setHasImage(view.isHasImage());
        else view.setHasImage(entity.isHasImage());
    }
}
