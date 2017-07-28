package com.cwbusinesservices.services.blocks;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.BlockEntity;
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
    public Integer create(BlockView view) throws BaseException, IllegalAccessException, InstantiationException {
        throw new UnsupportedOperationException("block.error.create");
    }

    @Override
    public boolean delete(Integer id) throws BaseException {
        throw new UnsupportedOperationException("block.error.delete");
    }

    public abstract Map<String,Object> getByCode(String code, Set<String> fields) throws BaseException;
}
