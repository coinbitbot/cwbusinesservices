package com.cwbusinesservices.services.blocks;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.service_error.ActionNotAllowedException;
import com.cwbusinesservices.pojo.entities.BlockEntity;
import com.cwbusinesservices.pojo.enums.BlockCodesEnum;
import com.cwbusinesservices.pojo.view.BlockView;
import com.cwbusinesservices.services.BaseService;

import java.util.Map;
import java.util.Set;

/**
 * Created by Andrii on 28.07.2017.
 */
public abstract class IBlockService extends BaseService<BlockEntity,BlockView,Integer>{

    public IBlockService(){
        super(BlockEntity.class);
    }

    @Override
    public Integer create(BlockView view) throws BaseException {
        throw new ActionNotAllowedException("block.error.create");
    }

    @Override
    public boolean delete(Integer id) throws BaseException {
        throw new ActionNotAllowedException("block.error.delete");
    }

    public abstract Map<String,Object> getByCode(BlockCodesEnum code, Set<String> fields) throws BaseException;
}
