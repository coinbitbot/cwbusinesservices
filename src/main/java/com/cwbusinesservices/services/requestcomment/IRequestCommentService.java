package com.cwbusinesservices.services.requestcomment;

import com.cwbusinesservices.pojo.entities.RequestCommentEntity;
import com.cwbusinesservices.pojo.enums.FileEntityTypeEnum;
import com.cwbusinesservices.pojo.view.RequestCommentView;
import com.cwbusinesservices.services.FileWorkBaseService;

/**
 * Created by Andrii on 29.07.2017.
 */
public abstract class IRequestCommentService extends FileWorkBaseService<RequestCommentEntity,RequestCommentView,Integer> {
    public IRequestCommentService(){
        super(RequestCommentEntity.class, FileEntityTypeEnum.REQUEST_COMMENT);
    }
}
