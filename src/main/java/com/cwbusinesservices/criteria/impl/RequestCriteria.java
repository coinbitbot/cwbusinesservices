package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.*;
import com.cwbusinesservices.pojo.enums.RequestStatusEnum;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 29.07.2017.
 */
public class RequestCriteria extends Criteria<RequestEntity>{
    private String query;
    private List<Integer> ids;
    private List<Integer> request_comments_ids;
    private List<Integer> user_ids;
    private List<Integer> industry_ids;
    private List<Integer> interest_ids;
    private RequestStatusEnum status;
    private Boolean has_file;

    public RequestCriteria(){
        super(0,0,RequestEntity.class);
    }

    public RequestCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public RequestCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,RequestEntity.class);
        RequestCriteria parsed = parse(restriction, RequestCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
            this.ids = parsed.ids;
            this.has_file = parsed.has_file;
            this.request_comments_ids = parsed.request_comments_ids;
            this.user_ids = parsed.user_ids;
            this.industry_ids = parsed.industry_ids;
            this.interest_ids = parsed.interest_ids;
            this.status = parsed.status;
        }
    }

    @Override
    public List<Predicate> query(Root<RequestEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';
            Expression<String> expression = root.get("companyName");
            predicates.add(cb.like(expression, likeQuery));
        }
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");
            predicates.add(exception.in(this.ids));
        }
        if (this.user_ids!=null&&this.user_ids.size()>0){
            Join<RequestEntity, UserEntity> requests = root.join("user");
            Expression<Integer> expression = requests.get("id");
            predicates.add(expression.in(this.user_ids));
        }
        if (this.request_comments_ids!=null&&this.request_comments_ids.size()>0){
            Join<RequestEntity, RequestCommentEntity> users = root.join("requestComments");
            Expression<Integer> expression = users.get("id");
            predicates.add(expression.in(this.request_comments_ids));
        }
        if (this.industry_ids!=null&&this.industry_ids.size()>0){
            Join<RequestCommentEntity, IndustryEntity> users = root.join("industry");
            Expression<Integer> expression = users.get("id");
            predicates.add(expression.in(this.industry_ids));
        }
        if (this.interest_ids!=null&&this.interest_ids.size()>0){
            Join<RequestCommentEntity, InterestEntity> users = root.join("interests");
            Expression<Integer> expression = users.get("id");
            predicates.add(expression.in(this.interest_ids));
        }
        if (this.has_file!=null){
            Expression<Boolean> expression = root.get("hasFile");
            predicates.add(cb.equal(expression, this.has_file));
        }
        if (this.status!=null){
            Expression<RequestStatusEnum> expression = root.get("status");
            predicates.add(cb.equal(expression, this.status));
        }
        return predicates;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getRequest_comments_ids() {
        return request_comments_ids;
    }

    public void setRequest_comments_ids(List<Integer> request_comments_ids) {
        this.request_comments_ids = request_comments_ids;
    }

    public List<Integer> getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(List<Integer> user_ids) {
        this.user_ids = user_ids;
    }

    public List<Integer> getIndustry_ids() {
        return industry_ids;
    }

    public void setIndustry_ids(List<Integer> industry_ids) {
        this.industry_ids = industry_ids;
    }

    public List<Integer> getInterest_ids() {
        return interest_ids;
    }

    public void setInterest_ids(List<Integer> interest_ids) {
        this.interest_ids = interest_ids;
    }

    public RequestStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RequestStatusEnum status) {
        this.status = status;
    }

    public Boolean getHas_file() {
        return has_file;
    }

    public void setHas_file(Boolean has_file) {
        this.has_file = has_file;
    }
}
