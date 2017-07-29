package com.cwbusinesservices.mergers;

import com.cwbusinesservices.pojo.entities.RoleEntity;
import com.cwbusinesservices.pojo.view.RoleView;
import org.springframework.stereotype.Service;

/**
 * Created by Oleh on 29.07.2017.
 */
@Service
public class RoleMerger implements Merger<RoleEntity, RoleView> {

    @Override
    public void merge(RoleEntity entity, RoleView view) {
        // mock method
    }

}
