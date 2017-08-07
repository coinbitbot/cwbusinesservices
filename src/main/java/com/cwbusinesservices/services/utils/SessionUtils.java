package com.cwbusinesservices.services.utils;

import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.pojo.helpers.IHasOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.cwbusinesservices.exceptions.service_error.AuthRequiredException;
import com.cwbusinesservices.persistence.dao.repositories.UsersRepository;
import com.cwbusinesservices.pojo.entities.PermissionEntity;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.pojo.enums.RolesEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrii on 10.09.2016.
 */
@Service
public class SessionUtils {

    @Autowired
    private UsersRepository usersRepository;

    public boolean isOwner(IHasOwner entity){
        UserEntity owner = entity.getUser();
        UserEntity currentUser = getCurrentUser();
        if (owner==null||currentUser==null)
            return false;
        if (owner.getId()==currentUser.getId())
            return true;
        return false;
    }

    public UserEntity getCurrentUser() {
        if (isAuthorized()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserEntity entity = (UserEntity)authentication.getPrincipal();

            return usersRepository.findByEmail(entity.getEmail());
        } else
            return null;
    }
    public boolean isAuthorized() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return !(authentication instanceof AnonymousAuthenticationToken);
    }

    public void authorized() throws AuthRequiredException {
        if(!isAuthorized()){
            throw new AuthRequiredException();
        }
    }

    public boolean isAdmin() {
        return isUserWithRole(RolesEnum.admin, RolesEnum.moderator);
    }

    public boolean isUserWithRole(RolesEnum... userRoles){
        if(isAuthorized()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            for (RolesEnum rolesEnum : userRoles) {
                if (authentication.getAuthorities().contains(ROLES_MAP.get(rolesEnum.name()))) {
                    return true; // user has this role, so it's not forbidden
                }
            }
        }
        return false;
    }

    public boolean isUserWithPermission(PermissionsEnum... permissions){
        if(isAuthorized()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            for (PermissionsEnum permission : permissions)
                if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(permission.toString())))
                    return true;
        }
        return false;
    }

    static final Map<String , SimpleGrantedAuthority> ROLES_MAP = new HashMap<String , SimpleGrantedAuthority>() {{
        put("admin",    new SimpleGrantedAuthority("ROLE_ADMIN"));
        put("moderator", new SimpleGrantedAuthority("ROLE_MODERATOR"));
        put("user",   new SimpleGrantedAuthority("ROLE_USER"));
    }};


    private List<GrantedAuthority> getGrantedAuthorities(UserEntity user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (PermissionEntity perm:user.getRoleEntity().getPermissions()){
            authorities.add(new SimpleGrantedAuthority(perm.getName()));
        }
        return authorities;
    }
}
