package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.enums.EmailTemplateCodeEnum;
import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Andrii on 31.07.2017.
 */
@Entity
@Table(name="EMAIL_TEMPLATE")
public class EmailTemplateEntity extends CompareIntegerId implements Serializable, GetableById<Integer> {

    private static final long serialVersionUID = 5669588896228636592L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="CODE")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "email.template.code.required")
    private EmailTemplateCodeEnum code;

    @Column(name="TEXT")
    @NotNull(message = "email.template.text.required")
    private String text;

    @Column(name="SUBJECT")
    @NotNull(message = "email.template.subject.required")
    @Size(max=350, message = "email.template.subject.size")
    private String subject;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
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

        EmailTemplateEntity that = (EmailTemplateEntity) o;

        if (id != that.id) return false;
        if (code != that.code) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return subject != null ? subject.equals(that.subject) : that.subject == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmailTemplateEntity{" +
                "id=" + id +
                ", code=" + code +
                ", text='" + text + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
