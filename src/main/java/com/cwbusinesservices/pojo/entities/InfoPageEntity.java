package com.cwbusinesservices.pojo.entities;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Andrii on 25.07.2017.
 */
@Entity
@Table(name="INFO_PAGES")
public class InfoPageEntity implements Serializable, GetableById<Integer>{

    private static final long serialVersionUID = -6206383240428127461L;
    public static final int MAX_URL_SIZE = 150;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="URL")
    @NotNull(message = "info.page.url.required")
    @Size(max = MAX_URL_SIZE, message = "info.page.url.max.size")
    private String url;

    @Column(name="HEADER")
    @NotNull(message = "info.page.header.required")
    private String header;

    @Column(name = "SUB_HEADER")
    private String subHeader;

    @Column(name = "TEXT")
    private String text;

    @Column(name="STATUS")
    private boolean active;

    @Column(name = "META_TITLE")
    private String metaTitle;

    @Column(name = "META_DESCRIPTION")
    private String metaDescription;

    @Column(name="META_KEYWORDS")
    private String metaKeywords;

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSubHeader() {
        return subHeader;
    }

    public void setSubHeader(String subHeader) {
        this.subHeader = subHeader;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoPageEntity that = (InfoPageEntity) o;

        if (id != that.id) return false;
        if (active != that.active) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (header != null ? !header.equals(that.header) : that.header != null) return false;
        if (subHeader != null ? !subHeader.equals(that.subHeader) : that.subHeader != null) return false;
        return text != null ? text.equals(that.text) : that.text == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (subHeader != null ? subHeader.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InfoPageEntity{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", header='" + header + '\'' +
                ", subHeader='" + subHeader + '\'' +
                ", text='" + text + '\'' +
                ", active=" + active +
                ", metaTitle='" + metaTitle + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                ", metaKeywords='" + metaKeywords + '\'' +
                '}';
    }
}
