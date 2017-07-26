package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.entities.GetableById;

import java.io.Serializable;

/**
 * Created by Andrii on 26.07.2017.
 */
public class CompanyView implements Serializable,GetableById<Integer> {

    private static final long serialVersionUID = 3731762957032873019L;

    private Integer id;
    private String name;
    private Boolean hasLogo;
    private String text;
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasLogo() {
        return hasLogo;
    }

    public void setHasLogo(Boolean hasLogo) {
        this.hasLogo = hasLogo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyView that = (CompanyView) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (hasLogo != null ? !hasLogo.equals(that.hasLogo) : that.hasLogo != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return active != null ? active.equals(that.active) : that.active == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (hasLogo != null ? hasLogo.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CompanyView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hasLogo=" + hasLogo +
                ", text='" + text + '\'' +
                ", active=" + active +
                '}';
    }
}
