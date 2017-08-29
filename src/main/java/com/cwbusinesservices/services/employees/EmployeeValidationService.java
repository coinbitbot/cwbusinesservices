package com.cwbusinesservices.services.employees;

import com.cwbusinesservices.pojo.entities.EmployeeEntity;
import com.cwbusinesservices.pojo.enums.PermissionsEnum;
import com.cwbusinesservices.services.BaseValidator;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 29.08.2017.
 */
@Service
public class EmployeeValidationService extends BaseValidator<EmployeeEntity,Integer> implements IEmployeeValidationService{
    public EmployeeValidationService(){
        super(PermissionsEnum.CREATE_EMPLOYEE, PermissionsEnum.EDIT_EMPLOYEE,PermissionsEnum.DELETE_EMPLOYEE, EmployeeEntity.class);
    }
}
