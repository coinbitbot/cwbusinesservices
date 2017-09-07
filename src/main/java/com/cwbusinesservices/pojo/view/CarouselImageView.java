package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;

import java.io.Serializable;

/**
 * Created by Andrii on 29.08.2017.
 */
public class CarouselImageView extends CompareIntegerId implements Serializable,GetableById<Integer>,IHasFile {

    private static final long serialVersionUID = 1910696915495544735L;

    private int id;
    private String description;
    private Integer position;
    private Boolean hasIcon;

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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public Boolean isHasFile() {
        return hasIcon;
    }

    @Override
    public void setHasFile(boolean hasFile) {
        this.hasIcon = hasFile;
    }

}
