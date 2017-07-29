package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;

import java.io.Serializable;

/**
 * Created by Andrii on 29.07.2017.
 */
public class RequestCommentView extends CompareIntegerId implements Serializable,GetableById<Integer>, IHasFile {
    private static final long serialVersionUID = -995651614156603623L;

    private Integer id;
    private Integer user_id;
    private Integer request_id;
    private String text;
    private Boolean has_file;

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

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
