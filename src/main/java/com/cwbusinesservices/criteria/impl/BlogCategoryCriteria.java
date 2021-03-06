package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.BlogCategoryEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 02.08.2017.
 */
public class BlogCategoryCriteria extends Criteria<BlogCategoryEntity>{
    private List<Integer> ids;
    private String query;

    public BlogCategoryCriteria(){
        super(0,0,BlogCategoryEntity.class);
    }

    public BlogCategoryCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public BlogCategoryCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,BlogCategoryEntity.class);
        BlogCategoryCriteria parsed = parse(restriction, BlogCategoryCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
            this.ids = parsed.ids;
        }
    }

    @Override
    public List<Predicate> query(Root<BlogCategoryEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';

            Expression<String> expression = root.get("name");
            Predicate p1 = cb.like(expression, likeQuery);

            expression = root.get("code");
            Predicate p2 = cb.like(expression, likeQuery);

            predicates.add(cb.or(p1, p2));
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
