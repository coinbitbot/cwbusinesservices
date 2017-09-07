package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Andrii on 29.07.2017.
 */
@Entity
@Table(name="INDUSTRY")
public class IndustryEntity extends CompareIntegerId implements Serializable, GetableById<Integer>{

    private static final long serialVersionUID = -5961108393175393331L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="NAME")
    @NotNull(message = "industry.name.required")
    @Size(max = 250, message = "industry.name.size")
    private String name;

    @Column(name="POSITION")
    private int position;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndustryEntity that = (IndustryEntity) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IndustryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
