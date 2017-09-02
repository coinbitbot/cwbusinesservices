package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Andrii on 29.08.2017.
 */
@Entity
@Table(name="EMPLOYEES")
public class EmployeeEntity extends CompareIntegerId implements Serializable, GetableById<Integer>, IHasFile{

    private static final long serialVersionUID = -723581376961829824L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="NAME")
    @Size(max = 300, message = "error.employee.name.size")
    @NotNull(message = "error.employee.name.not.null")
    private String name;

    @Column(name="POSITION")
    @Size(max = 500, message = "error.employee.position.size")
    @NotNull(message = "error.employee.position.not.null")
    private String position;

    @Column(name="EMAIL")
    @Email(message = "error.employee.email.format")
    @Size(max = 150, message = "error.employee.email.size")
    private String email;

    @Column(name ="PHONE")
    @Size(max = 45, message = "error.employee.phone.size")
    private String phone;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name="HAS_ICON")
    private boolean hasImage;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Boolean isHasFile() {
        return hasImage;
    }

    @Override
    public void setHasFile(boolean hasFile) {
        this.hasImage = hasFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", hasImage='" + hasImage + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
