package com.cwbusinesservices.services;

import com.cwbusinesservices.exceptions.BaseException;

/**
 * Created by Andrii on 28.07.2017.
 */
public interface IImageWork<I> {
    boolean changeImgStatus(I id, boolean status) throws BaseException;
    boolean hasImg(I id) throws BaseException;
}
