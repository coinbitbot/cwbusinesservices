package com.cwbusinesservices.services.mailing.emails;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.UserEntity;

/**
 * Created by Andrii on 04.08.2017.
 */
public interface IEmailService {
    void sendRequestFinishedEmail(UserEntity user) throws BaseException;
}
