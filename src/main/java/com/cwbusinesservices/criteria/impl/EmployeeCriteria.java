package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
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
public class EmployeeCriteria extends Criteria<EmployeeEntity>{
    private List<Integer> ids;
    private String query;

    public EmployeeCriteria(){
        super(0,0,EmployeeEntity.class);
    }

    public EmployeeCriteria(String restriction) throws WrongRestrictionException{
        this(0,0,restriction);
    }

    public EmployeeCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,EmployeeEntity.class);
        EmployeeCriteria parsed = parse(restriction, EmployeeCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
            this.ids = parsed.ids;
        }
    }

    @Override
    public List<Predicate> query(Root<EmployeeEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';

            Expression<String> expression = root.get("name");
            Predicate p1 = cb.like(expression, likeQuery);

            expression = root.get("position");
            Predicate p2 = cb.like(expression, likeQuery);

            expression = root.get("email");
            Predicate p3 = cb.like(expression, likeQuery);

            expression = root.get("phone");
            Predicate p4 = cb.like(expression, likeQuery);

            expression = root.get("description");
            Predicate p5 = cb.like(expression, likeQuery);

            predicates.add(cb.or(p1, p2,p3,p4,p5));
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
