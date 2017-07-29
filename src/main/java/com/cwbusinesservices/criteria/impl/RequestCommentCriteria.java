package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.RequestCommentEntity;
import com.cwbusinesservices.pojo.entities.RequestEntity;
import com.cwbusinesservices.pojo.entities.UserEntity;

import javax.persistence.criteria.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii on 29.07.2017.
 */
public class RequestCommentCriteria extends Criteria<RequestCommentEntity>{
    private String query;
    private List<Integer> ids;
    private Boolean has_file;
    private List<Integer> request_ids;
    private List<Integer> user_ids;
    private String date_from;
    private String date_till; //toUTCString

    public RequestCommentCriteria(){
        super(0,0,RequestCommentEntity.class);
    }

    public RequestCommentCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public RequestCommentCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,RequestCommentEntity.class);
        RequestCommentCriteria parsed = parse(restriction, RequestCommentCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
            this.ids = parsed.ids;
            this.has_file = parsed.has_file;
            this.request_ids = parsed.request_ids;
            this.user_ids = parsed.user_ids;
            this.date_from = parsed.date_from;
            this.date_till = parsed.date_till;
        }
    }

    @Override
    public List<Predicate> query(Root<RequestCommentEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';
            Expression<String> expression = root.get("text");
            predicates.add(cb.like(expression, likeQuery));
        }
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");
            predicates.add(exception.in(this.ids));
        }
        if (this.request_ids!=null&&this.request_ids.size()>0){
            Join<RequestCommentEntity, RequestEntity> requests = root.join("request");
            Expression<Integer> expression = requests.get("id");
            predicates.add(expression.in(this.request_ids));
        }
        if (this.user_ids!=null&&this.user_ids.size()>0){
            Join<RequestCommentEntity, UserEntity> users = root.join("user");
            Expression<Integer> expression = users.get("id");
            predicates.add(expression.in(this.user_ids));
        }
        if (this.has_file!=null){
            Expression<Boolean> expression = root.get("hasFile");
            predicates.add(cb.equal(expression, this.has_file));
        }
        if (this.date_from!=null&&!"".equals(date_from)){
            Date date = convertDate(this.date_from);
            Expression<Date> expression = root.get("date");
            predicates.add(cb.greaterThanOrEqualTo(expression,date));
        }
        if (this.date_till!=null&&!"".equals(date_till)){
            Date date = convertDate(this.date_till);
            Expression<Date> expression = root.get("date");
            predicates.add(cb.lessThanOrEqualTo(expression,date));
        }
        return predicates;
    }

    private Date convertDate(String dateInUtc){
        ZonedDateTime zdt = ZonedDateTime.parse(dateInUtc, DateTimeFormatter.RFC_1123_DATE_TIME);
        return Date.from(java.time.ZonedDateTime.now().toInstant());
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

    public Boolean getHas_file() {
        return has_file;
    }

    public void setHas_file(Boolean has_file) {
        this.has_file = has_file;
    }

    public List<Integer> getRequest_ids() {
        return request_ids;
    }

    public void setRequest_ids(List<Integer> request_ids) {
        this.request_ids = request_ids;
    }

    public List<Integer> getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(List<Integer> user_ids) {
        this.user_ids = user_ids;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_till() {
        return date_till;
    }

    public void setDate_till(String date_till) {
        this.date_till = date_till;
    }
}
