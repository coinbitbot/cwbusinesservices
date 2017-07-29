package com.cwbusinesservices.services.role;

import com.cwbusinesservices.criteria.impl.RoleCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ActionNotAllowedException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.persistence.dao.repositories.PermissionsRepository;
import com.cwbusinesservices.persistence.dao.repositories.RolesRepository;
import com.cwbusinesservices.pojo.entities.PermissionEntity;
import com.cwbusinesservices.pojo.entities.RoleEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.pojo.view.RoleView;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oleh on 29.07.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED)
public class RoleServiceImpl extends IRoleService {

    @Autowired
    private PermissionsRepository permissionsRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private SessionUtils sessionUtils;

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        RoleCriteria criteria = new RoleCriteria(restrict);
        return getList(criteria, fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        RoleCriteria criteria = new RoleCriteria(restrict);
        return count(criteria);
    }

    @Override
    public boolean delete(Integer id) throws BaseException {
        throw new ActionNotAllowedException("error.role.delete.not.allowed");
    }

    @Override
    public Integer create(RoleView view) throws BaseException, IllegalAccessException, InstantiationException {
        throw new ActionNotAllowedException("error.role.create.not.allowed");
    }

    @Override
    public boolean update(RoleView view) throws BaseException {
        throw new ActionNotAllowedException("error.role.edit.not.allowed");
    }


    @Override
    public boolean addPermission(RoleView view) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_PERMISSIONS_TO_ROLES))
            throw new ForbiddenException();

        RoleEntity role = getById(view.getId());
        PermissionEntity permission = permissionsRepository.findByName(view.getPermission().toString());

        if (permission == null) {
            throw new NoSuchEntityException("permission", "name: " + view.getPermission());
        }

        role.getPermissions().add(permission);

        role = rolesRepository.saveAndFlush(role);

        return role != null && role.getId() > 0;
    }

    @Override
    public boolean removePermission(int roleId, int permissionId) throws BaseException {
        if (!sessionUtils.isUserWithPermission(PermissionsEnum.EDIT_PERMISSIONS_TO_ROLES))
            throw new ForbiddenException();

        RoleEntity role = getById(roleId);
        PermissionEntity permission = permissionsRepository.findOne(permissionId);

        if (permission == null) {
            throw new NoSuchEntityException("permission", "id: " + permissionId);
        }

        role.getPermissions().remove(permission);

        role = rolesRepository.saveAndFlush(role);

        return role != null && role.getId() > 0;
    }
}
