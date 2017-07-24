package com.cwbusinesservices.criteria;

import com.google.gson.Gson;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by oleh_kurpiak on 14.10.2016.
 */
public abstract class Criteria<T> {

    private int offset;
    private int limit;

    private Class<T> entityClass;

    public Criteria(int offset, int limit, Class<T> entityClass) {
        this.offset = offset;
        this.limit = limit;
        this.entityClass = entityClass;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * implement this to set query params
     *
     * @param root - entity root.
     *             use <code>root.get("field_name")</code> to get the field from root entity
     * @param cb - create main query expressions
     *
     * @return list of predicates to be applied to query
     */
    public abstract List<Predicate> query(Root<T> root, CriteriaBuilder cb);

    public Query createQuery(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);

        Root<T> root = query.from(entityClass);
        query.select(root);

        List<Predicate> predicates = query(root, cb);
        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[predicates.size()]));
        }

        return em.createQuery(query);
    }

    public Query createCountQuery(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        Root<T> root = query.from(entityClass);
        query.select(cb.count(root));

        List<Predicate> predicates = query(root, cb);
        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[predicates.size()]));
        }

        return em.createQuery(query);
    }

    /**
     * return null if {#restriction} is empty or null
     *
     * @param restriction - json representation of object
     * @param clazz - class of object
     * @param <T> - type that extend Criteria
     * @return
     * @throws WrongRestrictionException - if passed wrong json format
     */
    protected <T extends Criteria> T parse(String restriction, Class<T> clazz) throws WrongRestrictionException {
        if(restriction == null || restriction.isEmpty() || restriction.equals("{}"))
            return null;

        try {
            Gson gson = new Gson();
            T parsed = gson.fromJson(restriction, clazz);

            return parsed;
        } catch (Exception e){
            throw new WrongRestrictionException();
        }
    }
}
