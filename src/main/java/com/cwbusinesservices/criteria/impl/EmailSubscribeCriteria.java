package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.EmailSubscribeEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 31.07.2017.
 */
public class EmailSubscribeCriteria extends Criteria<EmailSubscribeEntity> {
    private List<Integer> ids;
    private String email;
    private String query;

    public EmailSubscribeCriteria(){
        super(0,0,EmailSubscribeEntity.class);
    }

    public EmailSubscribeCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public EmailSubscribeCriteria(int offset,int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,EmailSubscribeEntity.class);
        EmailSubscribeCriteria parsed = parse(restriction, EmailSubscribeCriteria.class);
        if (parsed != null) {
            this.ids = parsed.ids;
            this.email = parsed.email;
            this.query = parsed.query;
        }
    }

    @Override
    public List<Predicate> query(Root<EmailSubscribeEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");
            predicates.add(exception.in(this.ids));
        }
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';
            Expression<String> expression = root.get("email");
            predicates.add(cb.like(expression, likeQuery));
        }
        if (this.email!=null){
            Expression<String> expression = root.get("email");
            predicates.add(cb.equal(expression, this.email));
        }
        return predicates;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
