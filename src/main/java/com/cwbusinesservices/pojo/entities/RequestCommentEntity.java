package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;
import com.cwbusinesservices.pojo.helpers.IHasOwner;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andrii on 29.07.2017.
 */
@Entity
@Table(name="REQUEST_COMMENT")
public class RequestCommentEntity extends CompareIntegerId implements Serializable, GetableById<Integer>, IHasFile,IHasOwner {

    private static final long serialVersionUID = 10449559075129067L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull(message = "request.comment.user.required")
    private UserEntity user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "REQUEST_ID")
    @NotNull(message = "request.comment.request.required")
    private RequestEntity request;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "HAS_FILE")
    private boolean hasFile;

    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @PrePersist
    public void onCreate(){
        date = new Date();
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RequestEntity getRequest() {
        return request;
    }

    public void setRequest(RequestEntity request) {
        this.request = request;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Boolean isHasFile() {
        return hasFile;
    }

    @Override
    public void setHasFile(boolean hasFile) {
        this.hasFile = hasFile;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestCommentEntity that = (RequestCommentEntity) o;

        if (id != that.id) return false;
        if (hasFile != that.hasFile) return false;
        if (user != null ? user.getId()!=that.user.getId() : that.user != null) return false;
        if (request != null ? request.getId()!=that.request.getId() : that.request != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.getId() : 0);
        result = 31 * result + (request != null ? request.getId() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (hasFile ? 1 : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
