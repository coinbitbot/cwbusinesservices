package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasImage;

import java.io.Serializable;

/**
 * Created by Andrii on 27.07.2017.
 */
public class ServiceView  extends CompareIntegerId implements Serializable,GetableById<Integer>, IHasImage {

    private static final long serialVersionUID = 8481507101886823362L;

    private Integer id;
    private String name;
    private Boolean has_icon;
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

    public Boolean getHas_icon() {
        return has_icon;
    }

    public void setHas_icon(Boolean has_icon) {
        this.has_icon = has_icon;
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
        if (has_icon != null ? !has_icon.equals(that.has_icon) : that.has_icon != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return active != null ? active.equals(that.active) : that.active == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (has_icon != null ? has_icon.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ServiceView{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", has_icon=").append(has_icon);
        sb.append(", description='").append(description).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Boolean isHasImage() {
        return getHas_icon();
    }

    @Override
    public void setHasImage(boolean hasImage) {
        setHas_icon(hasImage);
    }
}
