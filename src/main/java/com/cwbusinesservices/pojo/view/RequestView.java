package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.enums.RequestStatusEnum;
import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrii on 29.07.2017.
 */
public class RequestView extends CompareIntegerId implements Serializable,GetableById<Integer>, IHasFile {

    private static final long serialVersionUID = 8586573859447180350L;
    private Integer id;
    private Integer user_id;
    private List<Integer> request_comments;
    private Integer industry;
    private String interest_alter;
    private List<Integer> interests;
    private String company_name;
    private Boolean has_file;
    private RequestStatusEnum status;

    public RequestStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RequestStatusEnum status) {
        this.status = status;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public List<Integer> getRequest_comments() {
        return request_comments;
    }

    public void setRequest_comments(List<Integer> request_comments) {
        this.request_comments = request_comments;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public String getInterest_alter() {
        return interest_alter;
    }

    public void setInterest_alter(String interest_alter) {
        this.interest_alter = interest_alter;
    }

    public List<Integer> getInterests() {
        return interests;
    }

    public void setInterests(List<Integer> interests) {
        this.interests = interests;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Boolean getHas_file() {
        return has_file;
    }

    public void setHas_file(Boolean has_file) {
        this.has_file = has_file;
    }

    @Override
    public Boolean isHasFile() {
        return getHas_file();
    }

    @Override
    public void setHasFile(boolean hasFile) {
        setHas_file(hasFile);
    }
}
