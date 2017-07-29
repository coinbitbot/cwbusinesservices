package com.cwbusinesservices.mergers;

import com.cwbusinesservices.persistence.dao.repositories.RequestRepository;
import com.cwbusinesservices.persistence.dao.repositories.UsersRepository;
import com.cwbusinesservices.pojo.entities.RequestCommentEntity;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.pojo.view.RequestCommentView;
import com.cwbusinesservices.services.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 29.07.2017.
 */
@Component
public class RequestCommentMerger implements Merger<RequestCommentEntity,RequestCommentView>{

    @Autowired
    private Utils utils;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public void merge(RequestCommentEntity entity, RequestCommentView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (view.getUser_id()!=null){
            UserEntity user = usersRepository.findOne(view.getId());
            entity.setUser(user);
        }else if (entity.getUser()!=null) view.setUser_id(entity.getUser().getId());
        if (view.getRequest_id()!=null){
            RequestEntity request = requestRepository.findOne(view.getId());
            entity.setRequest(request);
        }else if (entity.getRequest()!=null) view.setRequest_id(entity.getRequest().getId());
        if (utils.notEmpty(view.getText())) entity.setText(view.getText());
        else view.setText(entity.getText());
        if (view.getHas_file()) entity.setHasFile(view.getHas_file());
        else view.setHas_file(entity.isHasFile());
    }
}
