package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrii on 01.08.2017.
 */
public class MenuItemView extends CompareIntegerId implements Serializable, GetableById<Integer> {

    private static final long serialVersionUID = 3333482085872833938L;

    private Integer id;
    private String name;
    private Integer menu;
    private Integer parent_menu;
    private List<Integer> child_items;
    private String url;
    private Integer position;

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

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    public Integer getParent_menu() {
        return parent_menu;
    }

    public void setParent_menu(Integer parent_menu) {
        this.parent_menu = parent_menu;
    }

    public List<Integer> getChild_items() {
        return child_items;
    }

    public void setChild_items(List<Integer> child_items) {
        this.child_items = child_items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItemView that = (MenuItemView) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (menu != null ? !menu.equals(that.menu) : that.menu != null) return false;
        if (parent_menu != null ? !parent_menu.equals(that.parent_menu) : that.parent_menu != null)
            return false;
        if (child_items != null ? !child_items.equals(that.child_items) : that.child_items != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        return !(position != null ? !position.equals(that.position) : that.position != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (menu != null ? menu.hashCode() : 0);
        result = 31 * result + (parent_menu != null ? parent_menu.hashCode() : 0);
        result = 31 * result + (child_items != null ? child_items.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuItemView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menu=" + menu +
                ", parent_menu=" + parent_menu +
                ", child_items=" + child_items +
                ", url='" + url + '\'' +
                ", position=" + position +
                '}';
    }
}
