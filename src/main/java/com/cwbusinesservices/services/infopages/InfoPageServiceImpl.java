package com.cwbusinesservices.services.infopages;

import com.cwbusinesservices.convertors.Converter;
import com.cwbusinesservices.criteria.impl.InfoPageCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.mergers.Merger;
import com.cwbusinesservices.persistence.criteria.ICriteriaRepository;
import com.cwbusinesservices.persistence.dao.repositories.InfoPagesRepository;
import com.cwbusinesservices.pojo.entities.InfoPageEntity;
import com.cwbusinesservices.pojo.view.InfoPageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 25.07.2017.
 */
@Service
public class InfoPageServiceImpl implements IInfoPageService{

    @Autowired
    private InfoPagesRepository repository;
    @Autowired
    private Converter<InfoPageEntity> converter;
    @Autowired
    private IInfoPageValidationService validationService;
    @Autowired
    private Merger<InfoPageEntity,InfoPageView> merger;

    @Autowired
    private ICriteriaRepository criteriaRepository;

    @Override
    public InfoPageEntity getById(int id) throws BaseException {
        InfoPageEntity entity = repository.findOne(id);
        if (entity == null)
            throw new NoSuchEntityException("InfoPage", "id: " + id);
        validationService.validForView(entity);
        return entity;
    }

    @Override
    public Map<String, Object> getById(int id, Set<String> fields) throws BaseException {
        return converter.convert(getById(id),fields);
    }

    @Override
    public List<InfoPageEntity> getList(InfoPageCriteria criteria) throws BaseException {
        List<InfoPageEntity> entities = criteriaRepository.find(criteria);
        if (entities == null || entities.isEmpty())
            throw new NoSuchEntityException("infoPages", criteria.toString());
        validationService.validForView(entities);
        return entities;
    }

    @Override
    public List<Map<String, Object>> getList(int offset, int limit, Set<String> fields, String restrict) throws BaseException {
        return converter.convert(getList(new InfoPageCriteria(offset,limit,restrict)),fields);
    }

    @Override
    public int create(InfoPageView view) throws BaseException {
        InfoPageEntity entity = new InfoPageEntity();
        merger.merge(entity,view);
        validationService.validForCreate(entity);
        entity = repository.saveAndFlush(entity);
        if(entity==null)
            throw new ServiceErrorException();
        return entity.getId();
    }

    @Override
    public boolean update(InfoPageView view) throws BaseException {
        InfoPageEntity entity = repository.findOne(view.getId());
        if (entity == null)
            throw new NoSuchEntityException(InfoPageEntity.class.getName(), "id" + view.getId());

        merger.merge(entity,view);
        validationService.validForUpdate(entity);
        entity = repository.saveAndFlush(entity);
        return entity != null && entity.getId() > 0;
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        InfoPageCriteria criteria = new InfoPageCriteria(restrict);
        return criteriaRepository.count(criteria);
    }

    @Override
    public boolean delete(int id) throws BaseException {
        InfoPageEntity entity = repository.findOne(id);
        if (entity == null)
            throw new NoSuchEntityException(InfoPageEntity.class.getName(), "id" + id);
        validationService.validForDelete(entity);
        repository.delete(entity);
        return true;
    }

    @Override
    public InfoPageEntity getByUrl(String url) throws BaseException {
        InfoPageEntity entity = repository.findByUrl(url);
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
