package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.enums.MenuCodeEnum;
import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrii on 01.08.2017.
 */
public class MenuView extends CompareIntegerId implements Serializable, GetableById<Integer> {

    private static final long serialVersionUID = -733033434382644800L;
    private Integer id;
    private MenuCodeEnum code;
    private String name;
    private List<Integer> menu_items;

    public List<Integer> getMenu_items() {
        return menu_items;
    }

    public void setMenu_items(List<Integer> menu_items) {
        this.menu_items = menu_items;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

        MenuView menuView = (MenuView) o;

        if (id != null ? !id.equals(menuView.id) : menuView.id != null) return false;
        if (code != menuView.code) return false;
        return !(name != null ? !name.equals(menuView.name) : menuView.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuView{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
