package com.cwbusinesservices.pojo.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Andrii on 18.08.2016.
 */
@Entity
@Table(name = "PERMISSIONS")
public class PermissionEntity implements Serializable{

    private static final long serialVersionUID = -4259492172487886880L;
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "NAME")
    private String name;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionEntity permissionEntity = (PermissionEntity) o;

        if (id != null ? !id.equals(permissionEntity.id) : permissionEntity.id != null) return false;
        if (name != null ? !name.equals(permissionEntity.name) : permissionEntity.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
