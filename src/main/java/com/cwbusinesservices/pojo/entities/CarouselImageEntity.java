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
@Table(name="CAROUSEL_IMAGES")
public class CarouselImageEntity extends CompareIntegerId implements Serializable, GetableById<Integer>, IHasFile{

    private static final long serialVersionUID = -723581376961829824L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "POSITION")
    private int position;

    @Column(name="HAS_ICON")
    private boolean hasImage;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

        CarouselImageEntity that = (CarouselImageEntity) o;

        if (id != that.id) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarouselImageEntity{" +
                "id=" + id +
                ", hasImage='" + hasImage + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
