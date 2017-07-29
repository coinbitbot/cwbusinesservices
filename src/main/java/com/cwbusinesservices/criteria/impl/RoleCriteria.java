package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.RoleEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleh on 29.07.2017.
 */
public class RoleCriteria extends Criteria<RoleEntity> {

    public RoleCriteria(String restrict) throws WrongRestrictionException {
        super(0, 0, RoleEntity.class);

        RoleCriteria parsed = parse(restrict, RoleCriteria.class);
        if (parsed != null) {
            if (parsed.getLimit() > 0)
                setLimit(parsed.getLimit());

            if (parsed.getOffset() > 0)
                setOffset(parsed.getOffset());
        }
    }

    public RoleCriteria(int offset, int limit) {
        super(offset, limit, RoleEntity.class);
    }

    @Override
    public List<Predicate> query(Root<RoleEntity> root, CriteriaBuilder cb) {
        return new ArrayList<>();
    }
}
