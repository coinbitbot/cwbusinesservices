package com.cwbusinesservices.services.infopages;

import com.cwbusinesservices.convertors.Converter;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.InfoPageCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.persistence.dao.repositories.InfoPagesRepository;
import com.cwbusinesservices.pojo.entities.InfoPageEntity;
import com.cwbusinesservices.services.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 25.07.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class InfoPageServiceImpl extends IInfoPageService{

    @Autowired
    private InfoPagesRepository infoPagesRepository;

    @Autowired
    private Converter<InfoPageEntity> converter;

    @Autowired
    private IValidator<InfoPageEntity> validationService;


    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<InfoPageEntity> criteria = new InfoPageCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        InfoPageCriteria criteria = new InfoPageCriteria(restrict);
        return count(criteria);
    }

    @Override
    public InfoPageEntity getByUrl(String url) throws BaseException {
        InfoPageEntity entity = infoPagesRepository.findByUrl(url);
        if (entity == null)
            throw new NoSuchEntityException(InfoPageEntity.class.getName(), "url " + url);
        validationService.validForView(entity);
        return entity;
    }

    @Override
    public Map<String, Object> getByUrl(String url, Set<String> fields) throws BaseException {
        return converter.convert(getByUrl(url),fields);
    }


}
