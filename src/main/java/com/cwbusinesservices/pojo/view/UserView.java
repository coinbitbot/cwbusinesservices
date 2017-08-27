package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.enums.RolesEnum;

import java.util.List;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
public class UserView implements GetableById<Integer> {

    private int id;
    private String email;
    private String password_new;
    private String password;
    private String last_name;
    private String first_name;
    private Boolean active;
    private String phone;
    private RolesEnum role = RolesEnum.user;
    private List<Integer> request_ids;
    private List<Integer> request_comment_ids;

    // just for full user registration
    private Integer industry;
    private String interest_alter;
    private List<Integer> interests;
    private String company_name;

    public void copyRequest(RequestView view) {
        view.setIndustry(industry);
        view.setInterest_alter(interest_alter);
        view.setInterests(interests);
        view.setCompany_name(company_name);
    }

    public List<Integer> getRequest_ids() {
        return request_ids;
    }

    public void setRequest_ids(List<Integer> request_ids) {
        this.request_ids = request_ids;
    }

    public List<Integer> getRequest_comment_ids() {
        return request_comment_ids;
    }

    public void setRequest_comment_ids(List<Integer> request_comment_ids) {
        this.request_comment_ids = request_comment_ids;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareId(int i) {
        return getId().compareTo(i);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_new() {
        return password_new;
    }

    public void setPassword_new(String password_new) {
        this.password_new = password_new;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public RolesEnum getRole() {
        return role;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserView userView = (UserView) o;

        if (id != userView.id) return false;
        if (email != null ? !email.equals(userView.email) : userView.email != null) return false;
        if (password != null ? !password.equals(userView.password) : userView.password != null) return false;
        if (last_name != null ? !last_name.equals(userView.last_name) : userView.last_name != null) return false;
        if (first_name != null ? !first_name.equals(userView.first_name) : userView.first_name != null) return false;
        if (active != null ? !active.equals(userView.active) : userView.active != null) return false;
        if (phone != null ? !phone.equals(userView.phone) : userView.phone != null) return false;
        return role == userView.role;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", active=" + active +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }
}
