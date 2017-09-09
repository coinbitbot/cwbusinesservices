package com.cwbusinesservices.services.mailing;
import com.cwbusinesservices.exceptions.BaseException;
import com.cwbusinesservices.pojo.entities.EmailTemplateEntity;
import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
import com.cwbusinesservices.services.templates.IEmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * Created by KutsykV on 24.01.2016.
 */
@Component
public class EmailBuilder {

    @Autowired
    private IEmailTemplateService service;

    public EmailTemplateEntity getEmailContent(EmailTemplateCodeEnum typeOfEmail, Map<String, String> data) throws BaseException {

        EmailTemplateEntity template = service.getByCode(typeOfEmail);
        return formEmailContent(template,data);
    }

    public EmailTemplateEntity getEmailTemplate(EmailTemplateCodeEnum typeOfEmail) throws BaseException {
        return service.getByCode(typeOfEmail);
    }

    public EmailTemplateEntity formEmailContent(EmailTemplateEntity template, Map<String, String> data){
        if (data!=null&&template!=null){
            String text = template.getText();
            String subject = template.getSubject();
            Iterator it = data.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry<String,String> pair = (Map.Entry)it.next();
                String key = "{{"+pair.getKey()+"}}";
                while (text.contains(key))
                    text = text.replace(key,pair.getValue());
                while (subject.contains(key))
                    subject = subject.replace(key,pair.getValue());
            }
            template.setText(text);
            template.setSubject(subject);
        }
        return template;
    }


}
