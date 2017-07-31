package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;

import java.io.Serializable;

/**
 * Created by Andrii on 31.07.2017.
 */
public class EmailTemplateView extends CompareIntegerId implements Serializable, GetableById<Integer> {

    private static final long serialVersionUID = -5407975837959712175L;

    private Integer id;
    private EmailTemplateCodeEnum code;
    private String text;
    private String subject;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmailTemplateCodeEnum getCode() {
        return code;
    }

    public void setCode(EmailTemplateCodeEnum code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailTemplateView that = (EmailTemplateView) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != that.code) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return subject != null ? subject.equals(that.subject) : that.subject == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmailTemplateView{" +
                "id=" + id +
                ", code=" + code +
                ", text='" + text + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
