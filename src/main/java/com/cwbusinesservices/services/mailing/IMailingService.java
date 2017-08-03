package com.cwbusinesservices.services.mailing;

import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.exceptions.not_found.NoSuchEntityException;
import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by KutsykV on 24.01.2016.
 */
public interface IMailingService {
    boolean sendEmailToUser(EmailTemplateCodeEnum typeOfEmail, String userEmail, Map<String, String> data, Locale locale) throws BaseException;
    boolean sendEmailToUsers(EmailTemplateCodeEnum typeOfEmail, List<String> users, Map<String, String> data, Locale locale) throws BaseException;
    String smtpListEmails(int limit, int offset, String from, String to, String sender, String recipient);
    boolean sendEmailToUser(EmailTemplateEntity content, String userEmail, Map<String, String> data, Locale locale) throws BaseException;
}
