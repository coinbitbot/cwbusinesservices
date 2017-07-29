package com.cwbusinesservices.services.company;

import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.pojo.view.CompanyView;
import com.cwbusinesservices.services.FileWorkBaseService;

/**
 * Created by Andrii on 26.07.2017.
 */
public abstract class ICompanyService extends FileWorkBaseService<CompanyEntity,CompanyView,Integer> {
    public ICompanyService(){
        super(CompanyEntity.class);
    }
}
