package com.cwbusinesservices.services.blog;

import com.cwbusinesservices.pojo.entities.PostEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 02.08.2017.
 */
@Service
public class PostValidationServiceImpl extends BaseValidator<PostEntity,Integer> implements IPostValidationService{
    public PostValidationServiceImpl(){
        super(PermissionsEnum.CREATE_POST, PermissionsEnum.EDIT_POST, PermissionsEnum.DELETE_POST, PostEntity.class);
    }
}
