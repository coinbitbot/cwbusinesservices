package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.enums.MenuCodeEnum;
import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Andrii on 01.08.2017.
 */
@Entity
@Table(name="MENU")
public class MenuEntity extends CompareIntegerId implements Serializable, GetableById<Integer> {

    private static final long serialVersionUID = 5370441127540203336L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="CODE")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "menu.code.required")
    private MenuCodeEnum code;

    @Column(name="NAME")
    private String name;

    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
    private Set<MenuItemEntity> menuItems;

    public Set<MenuItemEntity> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<MenuItemEntity> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MenuCodeEnum getCode() {
        return code;
    }

    public void setCode(MenuCodeEnum code) {
        this.code = code;
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

        MenuEntity menuItem = (MenuEntity) o;

        if (id != menuItem.id) return false;
        if (code != menuItem.code) return false;
        return !(name != null ? !name.equals(menuItem.name) : menuItem.name != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
