package com.cwbusinesservices.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.response.Error;
import com.cwbusinesservices.pojo.response.Response;

import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class ControllersExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BaseException.class)
    public @ResponseBody
    Response handler(BaseException e){
        Locale locale = LocaleContextHolder.getLocale();

        List<String> errors = e.formListErrors(messageSource, locale);
        Error error = new Error(e.getCode(), e.formMessage(messageSource, locale), errors);

        Response response = new Response();
        response.setError(error);

        return response;
    }
}
