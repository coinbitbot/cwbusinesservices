package com.cwbusinesservices.controllers.rest;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.RoleEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.pojo.response.Response;
import com.cwbusinesservices.pojo.view.RoleView;
import com.cwbusinesservices.services.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Oleh on 29.07.2017.
 */
@Controller
@RequestMapping("/api/role")
public class RoleApiController extends BaseApiController<RoleEntity, RoleView, Integer>{

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/permission", method = RequestMethod.PUT)
    public @ResponseBody
    Response<Boolean> addPermission(
            @RequestBody RoleView view
    ) throws BaseException {
        return responseFactory.get(roleService.addPermission(view));
    }

    @RequestMapping(value = "/{id}/permission", method = RequestMethod.DELETE)
    public @ResponseBody
    Response<Boolean> deletePermission(
            @PathVariable("id") int role,
            @RequestParam("permission") int permission
    ) throws BaseException {
        return responseFactory.get(roleService.removePermission(role, permission));
    }
}
