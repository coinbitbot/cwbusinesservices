package com.cwbusinesservices.criteria.impl;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.pojo.entities.BlogCategoryEntity;
import com.cwbusinesservices.pojo.entities.PostEntity;
import com.cwbusinesservices.services.utils.Utils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii on 02.08.2017.
 */
public class PostCriteria extends Criteria<PostEntity>{
    private String query;
    private List<Integer> ids;
    private Boolean has_img;
    private String date_from;
    private String date_till; //toUTCString
    private Integer category;
    private String category_code;

    public PostCriteria(){
        super(0,0,PostEntity.class);
    }

    public PostCriteria(String restriction) throws WrongRestrictionException {
        this(0,0,restriction);
    }

    public PostCriteria(int offset, int limit, String restriction) throws WrongRestrictionException{
        super(offset,limit,PostEntity.class);
        PostCriteria parsed = parse(restriction, PostCriteria.class);
        if (parsed != null) {
            this.query = parsed.query;
            this.ids = parsed.ids;
            this.date_from = parsed.date_from;
            this.date_till = parsed.date_till;
            this.has_img = parsed.has_img;
            this.category = parsed.category;
            this.category_code = parsed.category_code;
        }
    }

    @Override
    public List<Predicate> query(Root<PostEntity> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (this.query != null && !this.query.isEmpty()) {
            String likeQuery = '%' + this.query + '%';

            Expression<String> expression = root.get("title");
            Predicate p1 = cb.like(expression, likeQuery);

            expression = root.get("shortDescription");
            Predicate p2 = cb.like(expression, likeQuery);

            expression = root.get("postText");
            Predicate p3 = cb.like(expression, likeQuery);

            predicates.add(cb.or(p1, p2,p3));
        }
        if (this.ids != null && !this.ids.isEmpty()) {
            Expression<Integer> exception = root.get("id");
            predicates.add(exception.in(this.ids));
        }
        if (this.category!=null){
            Join<PostEntity, BlogCategoryEntity> requests = root.join("category");
            Expression<Integer> expression = requests.get("id");
            predicates.add(cb.equal(expression,category));
        }
        if (this.category_code != null && !this.category_code.isEmpty()){
            Join<PostEntity, BlogCategoryEntity> requests = root.join("category");
            Expression<String> expression = requests.get("code");
            predicates.add(cb.equal(expression, category_code));
        }
        if (this.has_img!=null){
            Expression<Boolean> expression = root.get("hasImg");
            predicates.add(cb.equal(expression, this.has_img));
        }
        if (this.date_from!=null&&!"".equals(date_from)){
            Date date = Utils.convertDate(this.date_from);
            Expression<Date> expression = root.get("date");
            predicates.add(cb.greaterThanOrEqualTo(expression,date));
        }
        if (this.date_till!=null&&!"".equals(date_till)){
            Date date = Utils.convertDate(this.date_till);
            Expression<Date> expression = root.get("date");
            predicates.add(cb.lessThanOrEqualTo(expression,date));
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

    public Boolean getHas_img() {
        return has_img;
    }

    public void setHas_img(Boolean has_img) {
        this.has_img = has_img;
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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PostCriteria{");
        sb.append("query='").append(query).append('\'');
        sb.append(", ids=").append(ids);
        sb.append(", has_img=").append(has_img);
        sb.append(", date_from='").append(date_from).append('\'');
        sb.append(", date_till='").append(date_till).append('\'');
        sb.append(", category=").append(category);
        sb.append(", category_code='").append(category_code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
