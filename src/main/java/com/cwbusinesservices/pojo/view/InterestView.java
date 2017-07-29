package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;

import java.io.Serializable;

/**
 * Created by Andrii on 29.07.2017.
 */
public class InterestView extends CompareIntegerId implements Serializable,GetableById<Integer>{

    private static final long serialVersionUID = -2165569826659513911L;
    private Integer id;
    private String name;

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

    @Override
    public String toString() {
        return "InterestView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterestView that = (InterestView) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
