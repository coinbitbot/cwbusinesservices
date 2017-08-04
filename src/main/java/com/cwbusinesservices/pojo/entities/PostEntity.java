package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.helpers.IHasFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andrii on 02.08.2017.
 */
@Entity
@Table(name="POST")
public class PostEntity extends CompareIntegerId implements Serializable, GetableById<Integer>,IHasFile{

    private static final long serialVersionUID = 2577891271148599614L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "TITLE")
    @NotNull(message = "post.title.required")
    private String title;

    @Column(name = "URL")
    @NotNull(message = "post.url.required")
    @Size(max = 150,message = "post.url.size")
    private String url;

    @Column(name="SHORT_DESCRIPTION")
    private String shortDescription;

    @Column(name = "DATE_OF_PUBLICATION")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name="HAS_IMG")
    private Boolean hasImg;

    @Column(name="META_TITLE")
    private String metaTitle;

    @Column(name="META_DESCRIPTION")
    private String metaDescription;

    @Column(name="META_KEYWORDS")
    private String metaKeywords;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BLOG_CATEGORY_ID", nullable = false)
    private BlogCategoryEntity category;

    @Column(name = "POST_TEXT")
    @NotNull(message = "post.text.required")
    private String postText;

    @PrePersist
    public void onCreate() {
        date = new Date();
        hasImg = false;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getHasImg() {
        return hasImg;
    }

    public void setHasImg(Boolean hasImg) {
        this.hasImg = hasImg;
    }

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

    public BlogCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(BlogCategoryEntity category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (shortDescription != null ? !shortDescription.equals(that.shortDescription) : that.shortDescription != null)
            return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (hasImg != null ? !hasImg.equals(that.hasImg) : that.hasImg != null) return false;
        if (metaTitle != null ? !metaTitle.equals(that.metaTitle) : that.metaTitle != null) return false;
        if (metaDescription != null ? !metaDescription.equals(that.metaDescription) : that.metaDescription != null)
            return false;
        return metaKeywords != null ? metaKeywords.equals(that.metaKeywords) : that.metaKeywords == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (hasImg != null ? hasImg.hashCode() : 0);
        result = 31 * result + (metaTitle != null ? metaTitle.hashCode() : 0);
        result = 31 * result + (metaDescription != null ? metaDescription.hashCode() : 0);
        result = 31 * result + (metaKeywords != null ? metaKeywords.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", date=" + date +
                ", hasImg=" + hasImg +
                ", metaTitle='" + metaTitle + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                ", metaKeywords='" + metaKeywords + '\'' +
                '}';
    }

    @Override
    public Boolean isHasFile() {
        return hasImg;
    }

    @Override
    public void setHasFile(boolean hasFile) {
        hasImg = hasFile;
    }
}
