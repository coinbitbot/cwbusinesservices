package com.cwbusinesservices.services.blog;

import com.cwbusinesservices.convertors.PostConverter;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.CompanyCriteria;
import com.cwbusinesservices.criteria.impl.PostCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.persistence.dao.repositories.PostRepository;
import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.pojo.entities.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 02.08.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class PostServiceImpl extends IPostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostConverter postConverter;

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<PostEntity> criteria = new PostCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<PostEntity> criteria = new PostCriteria(restrict);
        return count(criteria);
    }

    @Override
    public Map<String, Object> getByUrl(String url, Set<String> fields) throws BaseException {
        return postConverter.convert(getByUrl(url), fields);
    }

    @Override
    public PostEntity getByUrl(String url) throws BaseException {
        PostEntity entity = postRepository.findOneByUrl(url);
        if (entity == null) {
            throw new NoSuchEntityException("post", "url: " + url);
        }
        return entity;
    }
}
