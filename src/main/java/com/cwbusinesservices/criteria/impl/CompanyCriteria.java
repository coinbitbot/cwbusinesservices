package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongPasswordException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.CompanyEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 26.07.2017.
 */
public class CompanyCriteria extends Criteria<CompanyEntity> {

    private String query;
    private List<Integer> ids;
    private Boolean active;
    private Boolean has_logo;

    public CompanyCriteria(String restriction) throws WrongRestrictionException{
        this(0,0,restriction);
    }

    public CompanyCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,CompanyEntity.class);
        CompanyCriteria parsed = parse(restriction, CompanyCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
            this.ids = parsed.ids;
            this.has_logo = parsed.has_logo;
            this.active = parsed.active;
        }

    }

    @Override
    public List<Predicate> query(Root<CompanyEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.active != null) {
            Expression<Boolean> expression = root.get("active");
            predicates.add(cb.equal(expression, this.active));
        }
        if (this.has_logo != null) {
            Expression<Boolean> expression = root.get("hasLogo");
            predicates.add(cb.equal(expression, this.has_logo));
        }
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';

            Expression<String> expression = root.get("name");
            Predicate p1 = cb.like(expression, likeQuery);

            expression = root.get("text");
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
