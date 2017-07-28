package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.entities.GetableById;
import com.cwbusinesservices.pojo.entities.IHasImage;

import java.io.Serializable;

/**
 * Created by Andrii on 26.07.2017.
 */
public class CompanyView implements Serializable,GetableById<Integer>,IHasImage {

    private static final long serialVersionUID = 3731762957032873019L;

    private Integer id;
    private String name;
    private Boolean has_image;
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

    public Boolean getHas_image() {
        return has_image;
    }

    public void setHas_image(Boolean has_image) {
        this.has_image = has_image;
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
        if (has_image != null ? !has_image.equals(that.has_image) : that.has_image != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return active != null ? active.equals(that.active) : that.active == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (has_image != null ? has_image.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CompanyView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", has_image=" + has_image +
                ", text='" + text + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public Boolean isHasImage() {
        return getHas_image();
    }

    @Override
    public void setHasImage(boolean hasImage) {
        setHas_image(hasImage);
    }
}
