package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.MenuEntity;
import com.cwbusinesservices.pojo.enums.MenuCodeEnum;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 01.08.2017.
 */
public class MenuCriteria extends Criteria<MenuEntity> {

    private List<Integer> ids;
    private MenuCodeEnum code;

    public MenuCriteria(){
        super(0,0,MenuEntity.class);
    }

    public MenuCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public MenuCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,MenuEntity.class);
        MenuCriteria parsed = parse(restriction, MenuCriteria.class);
        if (parsed != null) {
            this.code = parsed.code;
            this.ids = parsed.ids;
        }

    }

    @Override
    public List<Predicate> query(Root<MenuEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");
            predicates.add(exception.in(this.ids));
        }
        if (this.code != null) {
            Expression<MenuCodeEnum> expression = root.get("code");
            predicates.add(cb.equal(expression, this.code));
        }
        return predicates;
    }
}
