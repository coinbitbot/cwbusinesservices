package com.cwbusinesservices.services.industry;

import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.IndustryCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.mergers.IndustryMerger;
import com.cwbusinesservices.mergers.Merger;
import com.cwbusinesservices.persistence.dao.repositories.IndustryRepository;
import com.cwbusinesservices.pojo.entities.IndustryEntity;
import com.cwbusinesservices.pojo.enums.OrderDirectionEnum;
import com.cwbusinesservices.pojo.view.IndustryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 29.07.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED, rollbackFor = BaseException.class)
public class IndustryServiceImpl extends IIndustryService{

    @Autowired
    private IndustryRepository repository;

    @Autowired
    private IndustryMerger merger;

    @Autowired
    private IIndustryValidationService iIndustryValidationService;

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<IndustryEntity> criteria = new IndustryCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<IndustryEntity> criteria = new IndustryCriteria(restrict);
        return count(criteria);
    }

    @Override
    public Integer create(IndustryView view) throws BaseException, IllegalAccessException, InstantiationException {
        IndustryEntity entity = new IndustryEntity();
        merger.merge(entity,view);

        try {
            IndustryCriteria criteria = new IndustryCriteria();
            criteria.setOrder_by("position");
            criteria.setOrder_direction(OrderDirectionEnum.DESC);
            criteria.setLimit(1);

            List<IndustryEntity> list = getList(criteria);

            entity.setPosition(list.get(0).getPosition() + 1);
        } catch (Exception e) {
            entity.setPosition(1);
        }

        iIndustryValidationService.validForCreate(entity);
        entity = repository.saveAndFlush(entity);
        if(entity==null)
            throw new ServiceErrorException();
        return entity.getId();
    }

    @Override
    public boolean swap(IndustryCriteria criteria) throws BaseException {
        criteria.setLimit(2);

        List<IndustryEntity> list = getList(criteria);
        if (list.size() != 2) {
            throw new ServiceErrorException();
        }

        IndustryEntity a = list.get(0);
        IndustryEntity b = list.get(1);

        int temp = a.getPosition();
        a.setPosition(b.getPosition());
        b.setPosition(temp);

        return true;
    }
}
