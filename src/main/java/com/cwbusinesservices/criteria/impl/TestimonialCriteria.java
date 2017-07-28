package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.TestimonialEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 27.07.2017.
 */
public class TestimonialCriteria extends Criteria<TestimonialEntity>{

    private String query;
    private List<Integer> ids;
    private Boolean active;

    public TestimonialCriteria() {
        super(0, 0, TestimonialEntity.class);
    }

    public TestimonialCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public TestimonialCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,TestimonialEntity.class);
        TestimonialCriteria parsed = parse(restriction, TestimonialCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
            this.ids = parsed.ids;
            this.active = parsed.active;
        }
    }

    @Override
    public List<Predicate> query(Root<TestimonialEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.active != null) {
            Expression<Boolean> expression = root.get("active");
            predicates.add(cb.equal(expression, this.active));
        }
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';

            Expression<String> expression = root.get("name");
            Predicate p1 = cb.like(expression, likeQuery);

            expression = root.get("text");
            Predicate p2 = cb.like(expression, likeQuery);

            expression = root.get("position");
            Predicate p3 = cb.like(expression, likeQuery);

            predicates.add(cb.or(p1, p2, p3));
        }
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");

            predicates.add(exception.in(this.ids));
        }
        return predicates;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
