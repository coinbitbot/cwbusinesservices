package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.pojo.helpers.GetableById;

/**
 * Created by Oleh on 29.07.2017.
 */
public class RoleView implements GetableById<Integer> {

    private int id;
    private PermissionsEnum permission;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PermissionsEnum getPermission() {
        return permission;
    }

    public void setPermission(PermissionsEnum permission) {
        this.permission = permission;
    }

    @Override
    public int compareId(int i) {
        return getId().compareTo(i);
    }
}
