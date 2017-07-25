package com.cwbusinesservices.services.utils;

import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 25.07.2017.
 */
@Service
public class Utils {

    public boolean notEmpty(String str){
        if (str!=null&&!"".equals(str))
            return true;
        return false;
    }

    public long numberOfPages(long totalNumber, long perPage) {
        long result = totalNumber / perPage;
        if (totalNumber % perPage != 0)
            return result + 1;
        return result;
    }

}
