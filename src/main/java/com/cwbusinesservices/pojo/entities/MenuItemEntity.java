package com.cwbusinesservices.pojo.entities;

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
@Table(name="MENU_ITEM")
public class MenuItemEntity extends CompareIntegerId implements Serializable, GetableById<Integer> {

    private static final long serialVersionUID = 1732057067770768847L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    @NotNull(message = "menu.item.name.required")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MENU_ID", nullable = true)
    private MenuEntity menu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_MENU_ITEM_ID", nullable = true)
    private MenuItemEntity parentMenuItem;

    @OneToMany(mappedBy = "parentMenuItem", fetch = FetchType.EAGER)
    private Set<MenuItemEntity> childItems;

    @Column(name = "URL")
    private String url;

    @Column(name="POSITION")
    private int position;

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

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    public MenuItemEntity getParentMenuItem() {
        return parentMenuItem;
    }

    public void setParentMenuItem(MenuItemEntity parentMenuItem) {
        this.parentMenuItem = parentMenuItem;
    }

    public Set<MenuItemEntity> getChildItems() {
        return childItems;
    }

    public void setChildItems(Set<MenuItemEntity> childItems) {
        this.childItems = childItems;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItemEntity that = (MenuItemEntity) o;

        if (id != that.id) return false;
        if (position != that.position) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(url != null ? !url.equals(that.url) : that.url != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + position;
        return result;
    }

    @Override
    public String toString() {
        return "MenuItemEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", position=" + position +
                '}';
    }
}
