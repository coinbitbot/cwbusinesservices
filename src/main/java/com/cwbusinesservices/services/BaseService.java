package com.cwbusinesservices.services;

/**
 * Created by Andrii on 25.07.2017.
 */

import com.cwbusinesservices.convertors.Converter;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.mergers.Merger;
import com.cwbusinesservices.persistence.criteria.ICriteriaRepository;
import com.cwbusinesservices.persistence.dao.repositories.BaseRepository;
import com.cwbusinesservices.pojo.helpers.GetableById;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @param <E> Entity class
 * @param <V> View class
 * @param <I> Entity primary key
 */

public abstract class BaseService<E extends GetableById<I>,V extends GetableById<I>,I extends Serializable> implements IService<E,V,I>{

    @Autowired
    private BaseRepository<E,I> repository;
    @Autowired
    private Converter<E> converter;
    @Autowired
    private Merger<E,V> merger;
    @Autowired
    private ICriteriaRepository criteriaRepository;
    @Autowired
    private IValidator<E> validationService;
    private Class<E> persistentClass;


    public BaseService(Class<E> entityClass){
        this.persistentClass = entityClass;
    }

    @Override
    public E getById(I id) throws BaseException {
        E entity = repository.findOne(id);
        if (entity == null)
            throw new NoSuchEntityException(persistentClass.getName() + "id: " + id);
        validationService.validForView(entity);
        return entity;
    }

    @Override
    public Map<String, Object> getById(I id, Set<String> fields) throws BaseException {
        return converter.convert(getById(id),fields);
    }

    @Override
    public List<E> getList(Criteria<E> criteria) throws BaseException {
        List<E> entities = criteriaRepository.find(criteria);
        if (entities == null || entities.isEmpty())
            throw new NoSuchEntityException(persistentClass.getName()+" "+criteria.toString());
        validationService.validForView(entities);
        return entities;
    }

    public abstract List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException;

    @Override
    public List<Map<String, Object>> getList(Criteria<E> criteria, Set<String> fields) throws BaseException{
        return converter.convert(getList(criteria),fields);
    }

    @Override
    public I create(V view) throws BaseException, IllegalAccessException, InstantiationException {
        E entity = persistentClass.newInstance();
        merger.merge(entity,view);
        validationService.validForCreate(entity);
        entity = repository.saveAndFlush(entity);
        if(entity==null)
            throw new ServiceErrorException();
        return entity.getId();
    }

    @Override
    public boolean update(V view) throws BaseException {
        E entity = repository.findOne(view.getId());
        if (entity == null)
            throw new NoSuchEntityException(persistentClass.getName(), "id" + view.getId());
        merger.merge(entity,view);
        validationService.validForUpdate(entity);
        entity = repository.saveAndFlush(entity);
        return entity != null;
    }

    @Override
    public abstract int count(String restrict) throws WrongRestrictionException;

    @Override
    public int count(Criteria<E> criteria){
        return criteriaRepository.count(criteria);
    }

    @Override
    public boolean delete(I id) throws BaseException {
        E entity = repository.findOne(id);
        if (entity == null)
            throw new NoSuchEntityException(persistentClass.getName(), "id" + id);
        validationService.validForDelete(entity);
        repository.delete(entity);
        return true;
    }

    public boolean save(E entity){
        entity = repository.saveAndFlush(entity);
        return entity != null;
    }
}
