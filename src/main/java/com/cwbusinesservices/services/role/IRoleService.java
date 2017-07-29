package com.cwbusinesservices.services.role;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.entities.RoleEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.pojo.view.RoleView;
import com.cwbusinesservices.services.BaseService;

/**
 * Created by Oleh on 29.07.2017.
 */
public abstract class IRoleService extends BaseService<RoleEntity, RoleView, Integer>{

    public IRoleService() {
        super(RoleEntity.class);
    }

    public abstract boolean addPermission(RoleView view) throws BaseException;

    public abstract boolean removePermission(int role, int permission) throws BaseException;
}
