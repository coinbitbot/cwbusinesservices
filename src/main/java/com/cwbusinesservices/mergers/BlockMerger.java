package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.BlockEntity;
import com.cwbusinesservices.pojo.view.BlockView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii on 28.07.2017.
 */
@Component
public class BlockMerger implements Merger<BlockEntity,BlockView>{

    @Autowired
    private Utils utils;


    @Override
    public void merge(BlockEntity entity, BlockView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (utils.notEmpty(view.getTitle())) entity.setTitle(view.getTitle());
        else view.setTitle(entity.getTitle());
    }
}
