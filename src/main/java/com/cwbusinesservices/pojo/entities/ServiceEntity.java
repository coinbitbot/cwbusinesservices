package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;
import com.cwbusinesservices.pojo.helpers.IHasImage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Andrii on 27.07.2017.
 */
@Entity
@Table(name="SERVICE")
public class ServiceEntity  extends CompareIntegerId implements Serializable, GetableById<Integer>, IHasFile {

    private static final long serialVersionUID = -713669045447385621L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="NAME")
    @NotNull(message = "service.name.required")
    private String name;

    @Column(name="HAS_ICON")
    private boolean hasImage;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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

        ServiceEntity that = (ServiceEntity) o;

        if (id != that.id) return false;
        if (hasImage != that.hasImage) return false;
        if (active != that.active) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (hasImage ? 1 : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hasImage=" + hasImage +
                ", description='" + description + '\'' +
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
