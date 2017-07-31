package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Andrii on 31.07.2017.
 */
@Entity
@Table(name = "EMAIL_SUBSCRIBE")
public class EmailSubscribeEntity extends CompareIntegerId implements Serializable, GetableById<Integer>{

    private static final long serialVersionUID = 6641497700407534712L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "EMAIL")
    @Email(message = "email.subscribe.email.wrong")
    @NotNull(message = "email.subscribe.email.required")
    private String email;

    @Override
    public Integer getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailSubscribeEntity that = (EmailSubscribeEntity) o;

        if (id != that.id) return false;
        return email != null ? email.equals(that.email) : that.email == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmailSubscribeEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
