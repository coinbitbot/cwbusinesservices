package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.entities.GetableById;

import java.io.Serializable;

/**
 * Created by Andrii on 27.07.2017.
 */
public class ServiceView implements Serializable,GetableById<Integer> {

    private static final long serialVersionUID = 8481507101886823362L;

    private Integer id;
    private String name;
    private Boolean hasIcon;
    private String description;
    private Boolean active;

    @Override
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

    public Boolean getHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(Boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        ServiceView that = (ServiceView) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (hasIcon != null ? !hasIcon.equals(that.hasIcon) : that.hasIcon != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return active != null ? active.equals(that.active) : that.active == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (hasIcon != null ? hasIcon.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hasIcon=" + hasIcon +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}