package com.cwbusinesservices.services.blog;

import com.cwbusinesservices.pojo.entities.PostEntity;
import com.cwbusinesservices.pojo.view.PostView;
import com.cwbusinesservices.services.FileWorkBaseService;

/**
 * Created by Andrii on 02.08.2017.
 */
public abstract class IPostService extends FileWorkBaseService<PostEntity,PostView,Integer> {
    public IPostService(){
        super(PostEntity.class);
    }
}
