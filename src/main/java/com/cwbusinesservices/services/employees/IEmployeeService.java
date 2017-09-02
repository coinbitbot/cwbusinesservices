package com.cwbusinesservices.services.employees;

import com.cwbusinesservices.pojo.entities.EmployeeEntity;
import com.cwbusinesservices.pojo.view.EmployeeView;
import com.cwbusinesservices.services.BaseService;
import com.cwbusinesservices.services.FileWorkBaseService;

/**
 * Created by Andrii on 29.08.2017.
 */
public abstract class IEmployeeService extends FileWorkBaseService<EmployeeEntity,EmployeeView,Integer> {
    public IEmployeeService(){super(EmployeeEntity.class);}
}
