package com.cwbusinesservices.pojo.helpers;

import com.cwbusinesservices.pojo.helpers.GetableById;

/**
 * Created by Andrii on 29.07.2017.
 */
public abstract class CompareIntegerId implements GetableById<Integer> {
    public int compareId(int i){
        return getId().compareTo(i);
    }
}
