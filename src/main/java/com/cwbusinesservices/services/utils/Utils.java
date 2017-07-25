package com.cwbusinesservices.services.utils;

import org.springframework.stereotype.Service;

/**
 * Created by Andrii on 25.07.2017.
 */
@Service
public class Utils {

    public static boolean notEmpty(String str){
        if (str!=null&&!"".equals(str))
            return true;
        return false;
    }

}
