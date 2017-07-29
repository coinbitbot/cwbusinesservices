package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.IndustryEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 29.07.2017.
 */
public class IndustryCriteria extends Criteria<IndustryEntity> {
    private List<Integer> ids;

    public IndustryCriteria(){super(0,0,IndustryEntity.class);}

    public IndustryCriteria(String restriction) throws WrongRestrictionException{
        this(0,0,restriction);
    }

    public IndustryCriteria(int offset,int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,IndustryEntity.class);
        IndustryCriteria parsed = parse(restriction, IndustryCriteria.class);
        if (parsed != null) {
            this.ids = parsed.ids;
        }
    }


    @Override
    public List<Predicate> query(Root<IndustryEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");

            predicates.add(exception.in(this.ids));
        }
        return predicates;
    }
}
