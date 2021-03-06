package com.cwbusinesservices.services.users;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.EmailRequiredException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.service_error.ForbiddenException;
import com.cwbusinesservices.mergers.UserMerger;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.pojo.view.RequestView;
import com.cwbusinesservices.services.mailing.IMailingService;
import com.cwbusinesservices.services.request.IRequestService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by Andrii on 18.08.2016.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BaseException.class)
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

    @Autowired
    private IRequestService requestService;

    @Autowired
    private IMailingService mailingService;

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
    @Transactional(propagation = Propagation.NEVER)
    public Integer create(UserView view) throws BaseException, InstantiationException, IllegalAccessException {
        if (view.getRole() == null)
            view.setRole(RolesEnum.user);
        view.setActive(true);
        view.setPassword("default_default");

        return super.create(view);
    }

    @Override
    public boolean update(UserView view) throws BaseException {
        UserEntity entity = getById(view.getId());
        if (entity == null)
            throw new NoSuchEntityException("user", "id " + view.getId());

        boolean authUserOnEmailChange = false;
        if (sessionUtils.getCurrentUser().compareId(entity.getId()) == 0) {
            if (view.getEmail() != null && !view.getEmail().equals(entity.getEmail())) {
                authUserOnEmailChange = true;
            }
        }

        userMerger.merge(entity,view);
        validateService.validForUpdate(entity);
        entity = repository.saveAndFlush(entity);

        if (entity != null && authUserOnEmailChange) {
            view.setEmail(entity.getEmail());
            view.setPassword(entity.getPassword());
            signInUser(view);
        }

        return entity != null;
    }

    public void update(UserEntity entity) {
        repository.saveAndFlush(entity);
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
    public boolean signInUser(String email, String token) throws BaseException {
        UserEntity user = getByEmail(email);
        if (!token.equals(getAuthorizationToken(user)))
            throw new ForbiddenException();

        UserView view = new UserView();
        view.setEmail(user.getEmail());
        view.setPassword(user.getPassword());

        return signInUser(view);
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
    public String getAuthorizationToken(UserEntity user) throws BaseException {
        String encript = user.getEmail()+userSolt;
        return encryptString(encript);
    }

    @Override
    public boolean changePassword(UserView view) throws BaseException {
        sessionUtils.authorized();

        UserEntity entity;

        if (sessionUtils.isUserWithRole(RolesEnum.admin) && view.getId() != null && view.getId() > 0) {
            entity = getById(view.getId());
        } else {
            entity = sessionUtils.getCurrentUser();

            // if equals it means that this is first registration
            if (! entity.getPassword().equals("default_default")) {
                if (!entity.getPassword().equals(view.getPassword())) {
                    throw new WrongPasswordException();
                }
            }
        }

        entity.setPassword(view.getPassword_new());
        validateService.validForUpdate(entity);
        entity = repository.saveAndFlush(entity);

        return entity != null;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Map<String, Object> registration(UserView user, HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws IllegalAccessException, BaseException, InstantiationException {
        try {
            final int userId = create(user);
            if (userId == 0)
                throw new ServiceErrorException();

            user.setId(userId);

            // request validation required immediately user sign in
            signInUser(user);

            RequestView request = new RequestView();
            user.copyRequest(request);
            request.setUser_id(userId);

            final int requestId = requestService.create(request);
            if (requestId == 0){
                delete(userId);
                throw new ServiceErrorException();
            }

            return new HashMap<String, Object>() {{
                put("user_id", userId);
                put("request_id", requestId);
            }};
        } catch (InstantiationException | IllegalAccessException | BaseException e ) {
            if (user.getId() != null && user.getId() > 0) {
                // user create - OK && request create - FAIL
                // user should be deleted

                repository.delete(user.getId());
            }

            logoutUser(servletRequest, servletResponse);
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public boolean sendActivationLinkToUser(HttpServletResponse response, HttpServletRequest request) throws BaseException {
        sessionUtils.authorized();

        UserEntity entity = sessionUtils.getCurrentUser();
        String token = getAuthorizationToken(entity);

        boolean result = mailingService.sendEmailToUser(
                EmailTemplateCodeEnum.NEW_USER_REGISTER,
                entity.getEmail(),
                new HashMap<String, String>(){{
                    put("url", hostUrl + "/helper/login/" + entity.getEmail() + "/" + token);
                    put("NAME", entity.getFullName());
                }}
        );

        // after registration have we should log out current user
        logoutUser(request, response);

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public boolean sendForgetPasswordEmail(UserView view) throws BaseException {
        if (StringUtils.isEmpty(view.getEmail()))
            throw new EmailRequiredException();

        UserEntity entity = getByEmail(view.getEmail());

        String randomAlphabetic = RandomStringUtils.randomAlphabetic(15);
        String password = DigestUtils.md5Hex(randomAlphabetic);
        entity.setPassword(password);
        update(entity);

        boolean result = mailingService.sendEmailToUser(
                EmailTemplateCodeEnum.RECOVER_PASSWORD,
                view.getEmail(),
                new HashMap<String, String>(){{
                    put("NAME", entity.getFirstName());
                    put("PASSWORD", randomAlphabetic);
                }}
        );

        return result;
    }

    private String encryptString(String encript) throws ServiceErrorException {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(encript.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceErrorException(e.getMessage());
        }
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



    @Value("${user.solt}")
    private String userSolt;

    @Value("${host.url}")
    private String hostUrl;
}
