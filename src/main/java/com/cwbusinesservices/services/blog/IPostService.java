package com.cwbusinesservices.services.blog;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.PostEntity;
import com.cwbusinesservices.pojo.view.PostView;
import com.cwbusinesservices.services.FileWorkBaseService;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 02.08.2017.
 */
public abstract class IPostService extends FileWorkBaseService<PostEntity,PostView,Integer> {
    public IPostService(){
        super(PostEntity.class);
    }

    public abstract Map<String,Object> getByUrl(String url, Set<String> fields) throws BaseException;
    public abstract PostEntity getByUrl(String url) throws BaseException;
}
