package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.BlockEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 28.07.2017.
 */
public class BlockCriteria extends Criteria<BlockEntity>{
    private List<Integer> ids;
    private String code;

    public BlockCriteria(){
        super(0,0,BlockEntity.class);
    }

    public BlockCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public BlockCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,BlockEntity.class);
        BlockCriteria parsed = parse(restriction, BlockCriteria.class);
        if (parsed != null) {
            this.code = parsed.code;
            this.ids = parsed.ids;
        }

    }

    @Override
    public List<Predicate> query(Root<BlockEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");
            predicates.add(exception.in(this.ids));
        }
        if (this.code != null) {
            Expression<Boolean> expression = root.get("code");
            predicates.add(cb.equal(expression, this.code));
        }
        return predicates;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
