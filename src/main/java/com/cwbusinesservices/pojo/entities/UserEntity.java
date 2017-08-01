package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.helpers.GetableById;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Andrii on 18.08.2016.
 */
@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable, GetableById<Integer>{

    private static final long serialVersionUID = 4276897615739047528L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @NotNull(message = "error.user.email.require")
    @Size(min = 1, max = MAX_EMAIL_SIZE, message = "error.user.email.max.size")
    @Email(message = "error.user.email.format")
    @Column(name = "EMAIL")
    private String email;

    @NotNull(message = "error.user.pass.require")
    @Size(min= MIN_PASS_SIZE,max = MAX_EMAIL_SIZE, message = "error.user.pass.size")
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ACTIVE")
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private RoleEntity roleEntity;

    @Column(name="PHONE")
    private String phone;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<RequestEntity> requests;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<RequestCommentEntity> requestComments;

    public static final int MAX_EMAIL_SIZE = 45;
    public static final int MAX_PASS_SIZE = 45;
    public static final int MIN_PASS_SIZE = 8;

    @PrePersist
    protected void onCreate() {
        active = false;
    }

    public Set<RequestEntity> getRequests() {
        return requests;
    }

    public void setRequests(Set<RequestEntity> requests) {
        this.requests = requests;
    }

    public Set<RequestCommentEntity> getRequestComments() {
        return requestComments;
    }

    public void setRequestComments(Set<RequestCommentEntity> requestComments) {
        this.requestComments = requestComments;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public int compareId(int i) {
        return getId().compareTo(i);
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (active != that.active) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (roleEntity != null ? roleEntity.getId()!=that.roleEntity.getId() : that.roleEntity != null) return false;
        return phone != null ? phone.equals(that.phone) : that.phone == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (roleEntity != null ? roleEntity.getId() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", active=" + active +
                ", roleEntity=" + roleEntity +
                ", phone='" + phone + '\'' +
                '}';
    }
}
