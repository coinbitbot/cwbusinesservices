package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.helpers.GetableById;

/**
 * Created by Oleh on 29.07.2017.
 */
public class RoleView implements GetableById<Integer> {
    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public int compareId(int i) {
        return 0;
    }
}
