package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 31.07.2017.
 */
public class EmailTemplateCriteria extends Criteria<EmailTemplateEntity> {
    private List<Integer> ids;
    private EmailTemplateCodeEnum code;
    private String query;

    public EmailTemplateCriteria(){
        super(0,0,EmailTemplateEntity.class);
    }

    public EmailTemplateCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public EmailTemplateCriteria(int offset,int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,EmailTemplateEntity.class);
        EmailTemplateCriteria parsed = parse(restriction, EmailTemplateCriteria.class);
        if (parsed != null) {
            this.ids = parsed.ids;
            this.code = parsed.code;
            this.query = parsed.query;
        }
    }

    @Override
    public List<Predicate> query(Root<EmailTemplateEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");
            predicates.add(exception.in(this.ids));
        }
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';
            Expression<String> expression = root.get("subject");
            Predicate p1 = cb.like(expression, likeQuery);
            expression = root.get("text");
            Predicate p2 = cb.like(expression, likeQuery);
            predicates.add(cb.or(p1, p2));
        }
        if (this.code!=null){
            Expression<EmailTemplateCodeEnum> expression = root.get("code");
            predicates.add(cb.equal(expression, this.code));
        }
        return predicates;
    }
}
