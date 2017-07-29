package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;
import com.cwbusinesservices.pojo.helpers.IHasImage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Andrii on 26.07.2017.
 */
@Entity
@Table(name="COMPANY")
public class CompanyEntity extends CompareIntegerId implements Serializable, GetableById<Integer>, IHasFile {

    private static final long serialVersionUID = 8132279800338374807L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="NAME")
    @NotNull(message = "company.name.required")
    private String name;

    @Column(name="HAS_LOGO")
    private boolean hasImage;

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

    public Boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
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

        CompanyEntity entity = (CompanyEntity) o;

        if (id != entity.id) return false;
        if (hasImage != entity.hasImage) return false;
        if (active != entity.active) return false;
        if (name != null ? !name.equals(entity.name) : entity.name != null) return false;
        return text != null ? text.equals(entity.text) : entity.text == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (hasImage ? 1 : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hasImage=" + hasImage +
                ", text='" + text + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public Boolean isHasFile() {
        return isHasImage();
    }

    @Override
    public void setHasFile(boolean hasFile) {
        setHasImage(hasFile);
    }
}
