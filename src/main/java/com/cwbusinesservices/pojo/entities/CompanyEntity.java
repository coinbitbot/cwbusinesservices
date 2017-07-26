package com.cwbusinesservices.pojo.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Andrii on 26.07.2017.
 */
@Entity
@Table(name="COMPANY")
public class CompanyEntity implements Serializable, GetableById<Integer> {

    private static final long serialVersionUID = 8132279800338374807L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="NAME")
    private String name;

    @Column(name="HAS_LOGO")
    private boolean hasLogo;

    @Column(name="TEXT")
    private String text;

    @Column(name="STATUS")
    private boolean active;

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

    public boolean isHasLogo() {
        return hasLogo;
    }

    public void setHasLogo(boolean hasLogo) {
        this.hasLogo = hasLogo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyEntity that = (CompanyEntity) o;

        if (id != that.id) return false;
        if (hasLogo != that.hasLogo) return false;
        if (active != that.active) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return text != null ? text.equals(that.text) : that.text == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (hasLogo ? 1 : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hasLogo=" + hasLogo +
                ", text='" + text + '\'' +
                ", active=" + active +
                '}';
    }
}
