package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.entities.GetableById;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by Andrii on 25.07.2017.
 */
public class InfoPageView implements Serializable,GetableById<Integer>{

    private static final long serialVersionUID = -6567992192957088108L;

    private Integer id;
    private String url;
    private String header;
    private String sub_header;
    private String text;
    private Boolean active;
    private String meta_title;
    private String meta_description;
    private String meta_keywords;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getSub_header() {
        return sub_header;
    }

    public void setSub_header(String sub_header) {
        this.sub_header = sub_header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getMeta_title() {
        return meta_title;
    }

    public void setMeta_title(String meta_title) {
        this.meta_title = meta_title;
    }

    public String getMeta_description() {
        return meta_description;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    public String getMeta_keywords() {
        return meta_keywords;
    }

    public void setMeta_keywords(String meta_keywords) {
        this.meta_keywords = meta_keywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoPageView that = (InfoPageView) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (header != null ? !header.equals(that.header) : that.header != null) return false;
        if (sub_header != null ? !sub_header.equals(that.sub_header) : that.sub_header != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return active != null ? active.equals(that.active) : that.active == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (sub_header != null ? sub_header.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InfoPageView{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", header='" + header + '\'' +
                ", sub_header='" + sub_header + '\'' +
                ", text='" + text + '\'' +
                ", active=" + active +
                ", meta_title='" + meta_title + '\'' +
                ", meta_description='" + meta_description + '\'' +
                ", meta_keywords='" + meta_keywords + '\'' +
                '}';
    }
}
