package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.helpers.IHasFile;
import com.cwbusinesservices.pojo.helpers.IHasImage;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 28.07.2017.
 */
@Component
public class FileMerger {
    /**
     *
     * @param view Entity view
     * @param entity Main entity
     */
    public void merge(IHasFile view, IHasFile entity){
        if(view.isHasFile()!=null) entity.setHasFile(view.isHasFile());
        else view.setHasFile(entity.isHasFile() != null && entity.isHasFile());
    }
}
