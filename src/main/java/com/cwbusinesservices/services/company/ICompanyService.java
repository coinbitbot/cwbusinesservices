package com.cwbusinesservices.services.company;

import com.cwbusinesservices.pojo.entities.CompanyEntity;
import com.cwbusinesservices.pojo.view.CompanyView;
import com.cwbusinesservices.services.BaseService;
import com.cwbusinesservices.services.IImageWork;
import com.cwbusinesservices.services.ImageWorkBaseService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Andrii on 26.07.2017.
 */
public abstract class ICompanyService extends ImageWorkBaseService<CompanyEntity,CompanyView,Integer>{
    public ICompanyService(){
        super(CompanyEntity.class);
    }
}
