package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.enums.RequestStatusEnum;
import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;
import com.cwbusinesservices.pojo.helpers.IHasOwner;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 29.07.2017.
 */
@Entity
@Table(name="REQUEST")
public class RequestEntity extends CompareIntegerId implements Serializable, GetableById<Integer>, IHasFile, IHasOwner {

    private static final long serialVersionUID = 1465809557806361801L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull(message = "request.user.required")
    private UserEntity user;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
    private Set<RequestCommentEntity> requestComments;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "INDUSTRY_ID")
    @NotNull(message = "request.industry.required")
    private IndustryEntity industry;

    @Column(name = "INTEREST_ALTER")
    private String interestAlter;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "HAS_BUSINESS_PLAN")
    private boolean hasFile;

    @ManyToMany
    @JoinTable(
            name="INTEREST_TO_REQUEST",
            joinColumns=@JoinColumn(name="REQUEST_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="INTERESTED_IN_ID", referencedColumnName="ID"))
    private List<InterestEntity> interests;

    @Column(name="STATUS")
    private RequestStatusEnum status;


    @PrePersist
    public void onCreate(){
        status = RequestStatusEnum.NEW;
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<RequestCommentEntity> getRequestComments() {
        return requestComments;
    }

    public void setRequestComments(Set<RequestCommentEntity> requestComments) {
        this.requestComments = requestComments;
    }

    public IndustryEntity getIndustry() {
        return industry;
    }

    public void setIndustry(IndustryEntity industry) {
        this.industry = industry;
    }

    public String getInterestAlter() {
        return interestAlter;
    }

    public void setInterestAlter(String interestAlter) {
        this.interestAlter = interestAlter;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public Boolean isHasFile() {
        return hasFile;
    }

    @Override
    public void setHasFile(boolean hasFile) {
        this.hasFile = hasFile;
    }

    public List<InterestEntity> getInterests() {
        return interests;
    }

    public void setInterests(List<InterestEntity> interests) {
        this.interests = interests;
    }
}
