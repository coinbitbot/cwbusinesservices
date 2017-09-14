package com.cwbusinesservices.aop;

import com.cwbusinesservices.criteria.impl.MenuItemCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.MenuItemEntity;
import com.cwbusinesservices.pojo.enums.MenuCodeEnum;
import com.cwbusinesservices.pojo.enums.OrderDirectionEnum;
import com.cwbusinesservices.services.menu.IMenuItemService;
import com.cwbusinesservices.services.menu.IMenuService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Oleh on 14.09.2017.
 */
@Aspect
public class MenuAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IMenuItemService menuItemService;

    @Pointcut("execution(* *(..)) && within(com.cwbusinesservices.controllers.web..*)")
    void plainWeb() {}

    @After("plainWeb()")
    public void addMenuToModel() {
        MenuItemCriteria criteria = new MenuItemCriteria();
        criteria.setMenu_code(MenuCodeEnum.MAIN);

        try {
            List<MenuItemEntity> all = menuItemService.getList(criteria);
            Set<MenuItemEntity> rootItems = all.stream()
                    .filter(item -> item.getParentMenuItem() == null)
                    .collect(Collectors.toSet());

            MenuItemEntity root = new MenuItemEntity();
            root.setChildItems(rootItems);

            request.setAttribute("menu", root);
        } catch (BaseException e) { }
    }
}
