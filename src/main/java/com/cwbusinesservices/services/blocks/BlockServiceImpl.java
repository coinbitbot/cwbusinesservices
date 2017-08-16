package com.cwbusinesservices.services.blocks;

import com.cwbusinesservices.convertors.BlockConverter;
import com.cwbusinesservices.criteria.Criteria;
import com.cwbusinesservices.criteria.impl.BlockCriteria;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.bad_request.WrongRestrictionException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.exceptions.service_error.ServiceErrorException;
import com.cwbusinesservices.persistence.dao.repositories.BlockRepository;
import com.cwbusinesservices.pojo.entities.BlockEntity;
import com.cwbusinesservices.pojo.enums.BlockCodesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 28.07.2017.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED, rollbackFor = BaseException.class)
public class BlockServiceImpl extends IBlockService{

    @Autowired
    private BlockConverter converter;

    @Override
    public Map<String, Object> getByCode(BlockCodesEnum code, Set<String> fields) throws BaseException  {
        BlockCriteria criteria = new BlockCriteria();
        criteria.setCode(code);
        List<BlockEntity> blocks = getList(criteria);
        if (blocks==null&&blocks.size()==0)
            throw new NoSuchEntityException(BlockEntity.class.getName(),"code "+code);
        if (blocks.size()>1)
            throw new ServiceErrorException();
        return converter.convert(blocks.get(0),fields);
    }

    @Override
    public List<Map<String, Object>> getList(Set<String> fields, String restrict) throws BaseException {
        Criteria<BlockEntity> criteria = new BlockCriteria(restrict);
        return getList(criteria,fields);
    }

    @Override
    public int count(String restrict) throws WrongRestrictionException {
        Criteria<BlockEntity> criteria = new BlockCriteria(restrict);
        return count(criteria);
    }
}
