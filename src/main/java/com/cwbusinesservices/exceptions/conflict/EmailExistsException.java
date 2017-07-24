package com.cwbusinesservices.exceptions.conflict;

import org.springframework.context.MessageSource;
import com.cwbusinesservices.exceptions.BaseException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
public class EmailExistsException extends BaseException {

    @Override
    public int getCode() {
        return HttpServletResponse.SC_CONFLICT;
    }

    @Override
    public String formMessage(MessageSource messageSource, Locale locale) {
        return messageSource.getMessage("errors.EmailExistsException", null, locale);
    }

    @Override
    public List<String> formListErrors(MessageSource messageSource, Locale locale) {
        return null;
    }
}
