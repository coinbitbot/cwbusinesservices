package com.cwbusinesservices.mergers;

import com.cwbusinesservices.persistence.dao.repositories.RolesRepository;
import com.cwbusinesservices.pojo.entities.RoleEntity;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.pojo.view.UserView;
import com.cwbusinesservices.services.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Oleh on 31.07.2017.
 */
@Service
public class UserMerger implements Merger<UserEntity, UserView> {

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public void merge(UserEntity entity, UserView view) {
        if(view.getFirst_name() != null)
            entity.setFirstName(view.getFirst_name());
        else view.setFirst_name(entity.getFirstName());

        if(view.getLast_name() != null)
            entity.setLastName(view.getLast_name());
        else view.setLast_name(entity.getLastName());

        if (view.getPhone()!=null&&!"".equals(view.getPhone())) entity.setPhone(view.getPhone());
        else view.setPhone(entity.getPhone());

        if(view.getEmail() != null)
            entity.setEmail(view.getEmail());
        else view.setEmail(entity.getEmail());

        if(view.getActive() != null)
            entity.setActive(view.getActive());
        else view.setActive(entity.isActive());

        if(view.getPassword() != null)
            entity.setPassword(view.getPassword());
        else view.setPassword(entity.getPassword());

        if (sessionUtils.isAdmin()) {
            if (view.getRole() != null) {
                RoleEntity role = rolesRepository.findByName(view.getRole());
                entity.setRoleEntity(role);
            } else if (entity.getRoleEntity() != null) {
                view.setRole(entity.getRoleEntity().getName());
            }
        }
    }
}
