package com.cwbusinesservices.pojo.view;


import org.hibernate.validator.constraints.Email;
import com.cwbusinesservices.pojo.enums.RolesEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
public class UserView {

    public static final int MAX_EMAIL_SIZE = 45;
    public static final int MAX_PASS_SIZE = 45;
    public static final int MIN_PASS_SIZE = 8;

    private int id;

    @NotNull(message = "error.user.email.require")
    @Size(max = MAX_EMAIL_SIZE, message = "error.user.email.max.size")
    @Email(message = "error.user.email.format")
    private String email;

    @NotNull(message = "error.user.pass.require")
    @Size(min= MIN_PASS_SIZE,max = MAX_EMAIL_SIZE, message = "error.user.pass.size")
    private String password;

    private String last_name;
    private String first_name;
    private Boolean active;
    private String phone;
    private RolesEnum role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
