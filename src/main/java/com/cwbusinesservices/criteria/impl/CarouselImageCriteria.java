package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.CarouselImageEntity;
import com.cwbusinesservices.pojo.entities.EmployeeEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 29.08.2017.
 */
public class CarouselImageCriteria extends Criteria<CarouselImageEntity>{
    private List<Integer> ids;
    private String query;

    public CarouselImageCriteria(){
        super(0,0,CarouselImageEntity.class);
    }

    public CarouselImageCriteria(String restriction) throws WrongRestrictionException{
        this(0,0,restriction);
    }

    public CarouselImageCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,CarouselImageEntity.class);
        CarouselImageCriteria parsed = parse(restriction, CarouselImageCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
            this.ids = parsed.ids;
        }
    }

    @Override
    public List<Predicate> query(Root<CarouselImageEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';

            Expression<String> expression = root.get("description");

            predicates.add(cb.like(expression, likeQuery));
        }
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");
            predicates.add(exception.in(this.ids));
        }
        return predicates;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
