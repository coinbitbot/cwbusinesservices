package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.ServiceEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 27.07.2017.
 */
public class ServiceCriteria extends Criteria<ServiceEntity>{

    private String query;
    private List<Integer> ids;
    private Boolean active;
    private Boolean has_img;

    public ServiceCriteria(String restriction) throws WrongRestrictionException{
        this(0,0,restriction);
    }

    public ServiceCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,ServiceEntity.class);
        ServiceCriteria parsed = parse(restriction, ServiceCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
            this.ids = parsed.ids;
            this.has_img = parsed.has_img;
            this.active = parsed.active;
        }
    }

    @Override
    public List<Predicate> query(Root<ServiceEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.active != null) {
            Expression<Boolean> expression = root.get("active");
            predicates.add(cb.equal(expression, this.active));
        }
        if (this.has_img != null) {
            Expression<Boolean> expression = root.get("hasImg");
            predicates.add(cb.equal(expression, this.has_img));
        }
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';

            Expression<String> expression = root.get("name");
            Predicate p1 = cb.like(expression, likeQuery);

            expression = root.get("description");
            Predicate p2 = cb.like(expression, likeQuery);

            predicates.add(cb.or(p1, p2));
        }
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");
            predicates.add(exception.in(this.ids));
        }
        return predicates;
    }
}
