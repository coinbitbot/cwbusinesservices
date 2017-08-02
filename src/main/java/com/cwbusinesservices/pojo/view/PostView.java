package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;

import java.io.Serializable;

/**
 * Created by Andrii on 02.08.2017.
 */
public class PostView extends CompareIntegerId implements Serializable, GetableById<Integer>,IHasFile {

    private static final long serialVersionUID = 7263669227094629879L;
    private Integer id;
    private String title;
    private String url;
    private String short_description;
    private String date_of_publication; //toUTCString
    private Boolean has_img;
    private String meta_title;
    private String meta_description;
    private String meta_keywords;
    private Integer category;
    private String post_text;

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDate_of_publication() {
        return date_of_publication;
    }

    public void setDate_of_publication(String date_of_publication) {
        this.date_of_publication = date_of_publication;
    }

    public Boolean getHas_img() {
        return has_img;
    }

    public void setHas_img(Boolean has_img) {
        this.has_img = has_img;
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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostView postView = (PostView) o;

        if (id != null ? !id.equals(postView.id) : postView.id != null) return false;
        if (title != null ? !title.equals(postView.title) : postView.title != null) return false;
        if (url != null ? !url.equals(postView.url) : postView.url != null) return false;
        if (short_description != null ? !short_description.equals(postView.short_description) : postView.short_description != null)
            return false;
        if (date_of_publication != null ? !date_of_publication.equals(postView.date_of_publication) : postView.date_of_publication != null)
            return false;
        if (has_img != null ? !has_img.equals(postView.has_img) : postView.has_img != null) return false;
        if (meta_title != null ? !meta_title.equals(postView.meta_title) : postView.meta_title != null) return false;
        if (meta_description != null ? !meta_description.equals(postView.meta_description) : postView.meta_description != null)
            return false;
        if (meta_keywords != null ? !meta_keywords.equals(postView.meta_keywords) : postView.meta_keywords != null)
            return false;
        return category != null ? category.equals(postView.category) : postView.category == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (short_description != null ? short_description.hashCode() : 0);
        result = 31 * result + (date_of_publication != null ? date_of_publication.hashCode() : 0);
        result = 31 * result + (has_img != null ? has_img.hashCode() : 0);
        result = 31 * result + (meta_title != null ? meta_title.hashCode() : 0);
        result = 31 * result + (meta_description != null ? meta_description.hashCode() : 0);
        result = 31 * result + (meta_keywords != null ? meta_keywords.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostView{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", short_description='" + short_description + '\'' +
                ", date_of_publication='" + date_of_publication + '\'' +
                ", has_img=" + has_img +
                ", meta_title='" + meta_title + '\'' +
                ", meta_description='" + meta_description + '\'' +
                ", meta_keywords='" + meta_keywords + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public Boolean isHasFile() {
        return has_img;
    }

    @Override
    public void setHasFile(boolean hasFile) {
        has_img = hasFile;
    }
}
