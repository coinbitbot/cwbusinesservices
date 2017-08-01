package com.cwbusinesservices.mergers;

import com.cwbusinesservices.persistence.dao.repositories.IndustryRepository;
import com.cwbusinesservices.persistence.dao.repositories.InterestRepository;
import com.cwbusinesservices.persistence.dao.repositories.RequestCommentRepository;
import com.cwbusinesservices.persistence.dao.repositories.UsersRepository;
import com.cwbusinesservices.pojo.entities.InterestEntity;
import com.cwbusinesservices.pojo.entities.RequestCommentEntity;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.pojo.view.RequestView;
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
public class RequestMerger implements Merger<RequestEntity,RequestView>{

    @Autowired
    private Utils utils;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RequestCommentRepository commentsRepository;
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private InterestRepository interestRepository;

    @Override
    public void merge(RequestEntity entity, RequestView view) {
        if (view.getId()!=null) entity.setId(view.getId());
        else view.setId(entity.getId());
        if (view.getUser_id()!=null){
            UserEntity user = usersRepository.findOne(view.getUser_id());
            entity.setUser(user);
        }else if (entity.getUser()!=null) view.setUser_id(entity.getUser().getId());
        if (view.getRequest_comments()!=null&&view.getRequest_comments().size()>0){
            Set<RequestCommentEntity> comments = new HashSet<>();
            for (Integer id:view.getRequest_comments()){
                RequestCommentEntity comment = commentsRepository.findOne(id);
                if (comment!=null)
                    comments.add(comment);
            }
            entity.setRequestComments(comments);
        }else if (entity.getRequestComments()!=null&&entity.getRequestComments().size()>0){
            List<Integer> ids = new LinkedList<>();
            for (RequestCommentEntity comment:entity.getRequestComments())
                ids.add(comment.getId());
            view.setRequest_comments(ids);
        }
        if (view.getIndustry()!=null){
            entity.setIndustry(industryRepository.findOne(view.getIndustry()));
        }else if (entity.getIndustry()!=null) view.setIndustry(entity.getIndustry().getId());
        if (utils.notEmpty(view.getInterest_alter())) entity.setInterestAlter(view.getInterest_alter());
        else view.setInterest_alter(entity.getInterestAlter());
        if (utils.notEmpty(view.getCompany_name())) entity.setCompanyName(view.getCompany_name());
        else view.setCompany_name(entity.getCompanyName());
        if (view.getHas_file()!=null) entity.setHasFile(view.getHas_file());
        else view.setHas_file(entity.isHasFile());
        if (view.getInterests()!=null&&view.getInterests().size()>0){
            List<InterestEntity> interests = new LinkedList<>();
            for (Integer id:view.getInterests()){
                InterestEntity interest = interestRepository.findOne(id);
                if (interest!=null)
                    interests.add(interest);
            }
            entity.setInterests(interests);
        }else if (entity.getInterests()!=null&&entity.getInterests().size()>0){
            List<Integer> ids = new LinkedList<>();
            for (InterestEntity interest:entity.getInterests())
                ids.add(interest.getId());
            view.setInterests(ids);
        }
    }
}
