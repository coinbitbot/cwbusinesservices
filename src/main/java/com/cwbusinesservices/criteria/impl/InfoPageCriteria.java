package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.InfoPageEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 25.07.2017.
 */
public class InfoPageCriteria extends Criteria<InfoPageEntity>{

    private String query;
    private List<Integer> ids;
    private String url;
    private Boolean active;

    public InfoPageCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public InfoPageCriteria(int offset, int limit, String restriction) throws WrongRestrictionException {
        super(offset, limit, InfoPageEntity.class);

        InfoPageCriteria parsed = parse(restriction, InfoPageCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
            this.ids = parsed.ids;
            this.url = parsed.url;
            this.active = parsed.active;
        }
    }

    @Override
    public List<Predicate> query(Root<InfoPageEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.active != null) {
            Expression<Boolean> expression = root.get("active");
            predicates.add(cb.equal(expression, this.active));
        }
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';

            Expression<String> expression = root.get("header");
            Predicate p1 = cb.like(expression, likeQuery);

            expression = root.get("subHeader");
            Predicate p2 = cb.like(expression, likeQuery);

            expression = root.get("text");
            Predicate p3 = cb.like(expression, likeQuery);

            predicates.add(cb.or(p1, p2, p3));
        }
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");

            predicates.add(exception.in(this.ids));
        }
        if (this.url!=null){
            Expression<String> expression = root.get("url");
            predicates.add(cb.equal(expression, this.url));
        }
        return predicates;
    }
}
