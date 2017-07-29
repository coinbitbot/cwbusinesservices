package com.cwbusinesservices.services;

import com.cwbusinesservices.exceptions.BaseException;

/**
 * Created by Andrii on 29.07.2017.
 */
public interface IFileWork<I> {
    boolean changeFileStatus(I id, boolean status) throws BaseException;
    boolean hasFile(I id) throws BaseException;
}
