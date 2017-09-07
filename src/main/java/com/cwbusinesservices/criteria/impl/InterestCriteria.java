package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.InterestEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 29.07.2017.
 */
public class InterestCriteria extends Criteria<InterestEntity> {
    private List<Integer> ids;

    public InterestCriteria(){super(0,0,InterestEntity.class);}

    public InterestCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public InterestCriteria(int offset,int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,InterestEntity.class);
        InterestCriteria parsed = parse(restriction, InterestCriteria.class);
        if (parsed != null) {
            this.ids = parsed.ids;
        }
    }


    @Override
    public List<Predicate> query(Root<InterestEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
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
}
