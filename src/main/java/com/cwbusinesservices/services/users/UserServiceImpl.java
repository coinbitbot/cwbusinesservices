package com.cwbusinesservices.services.users;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.mergers.UserMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.cwbusinesservices.convertors.Converter;
import com.cwbusinesservices.criteria.impl.UserCriteria;
import com.cwbusinesservices.exceptions.bad_request.WrongPasswordException;
import com.cwbusinesservices.exceptions.conflict.EmailExistsException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.exceptions.service_error.ValidationException;
import com.cwbusinesservices.persistence.criteria.ICriteriaRepository;
import com.cwbusinesservices.persistence.dao.repositories.RolesRepository;
import com.cwbusinesservices.persistence.dao.repositories.UsersRepository;
import com.cwbusinesservices.pojo.entities.PermissionEntity;
import com.cwbusinesservices.pojo.entities.RoleEntity;
import com.cwbusinesservices.pojo.entities.UserEntity;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.enums.RolesEnum;
import com.cwbusinesservices.pojo.view.UserView;
import com.cwbusinesservices.services.utils.SessionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 18.08.2016.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl extends IUserService {

    @Resource
    private UsersRepository repository;

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private IUserValidateService validateService;

    @Autowired
    private UserMerger userMerger;

    @Autowired
    private ICriteriaRepository criteriaRepository;

    @Override
    public UserEntity getByEmail(String email) throws NoSuchEntityException {
        UserEntity entity = repository.findByEmail(email);

        if (entity == null)
            throw new NoSuchEntityException("users", "user email " + email);

        return entity;
    }

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        UserCriteria criteria = new UserCriteria(restrict);

        return getList(criteria, fields);
    }

    @Override
    public Integer create(UserView view) throws BaseException {
        UserEntity entity = new UserEntity();
        if (view.getRole() == null)
            view.setRole(RolesEnum.user);
        view.setActive(true);

        if (sessionUtils.isAdmin()) {
            view.setPassword("default_default");
        }

        userMerger.merge(entity, view);
        validateService.validForCreate(entity);

        entity = repository.saveAndFlush(entity);
        if(entity == null){
            throw new ServiceErrorException();
        }

        if (!sessionUtils.isAuthorized()){
            sessionUtils.logeInUser(entity);
        }

        //TODO merge requests and comments
        return entity.getId();
    }

    @Override
    public boolean signInUser(UserView view) throws NoSuchEntityException, WrongPasswordException {
        UserEntity entity = getByEmail(view.getEmail());
        if(!entity.getPassword().equals(view.getPassword()))
            throw new WrongPasswordException();

        Authentication authentication = new UsernamePasswordAuthenticationToken(entity, entity.getPassword(), getGrantedAuthorities(entity));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        return true;
    }

    @Override
    public boolean logoutUser(HttpServletRequest request, HttpServletResponse response) {
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return true;
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        UserCriteria criteria = new UserCriteria(restrict);

        return criteriaRepository.count(criteria);
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserEntity user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (PermissionEntity perm : user.getRoleEntity().getPermissions()){
            authorities.add(new SimpleGrantedAuthority(perm.getName()));
        }
        return authorities;
    }
}
