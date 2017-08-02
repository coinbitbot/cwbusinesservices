package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Andrii on 02.08.2017.
 */
@Entity
@Table(name="BLOG_CATEGORY")
public class BlogCategoryEntity extends CompareIntegerId implements Serializable, GetableById<Integer> {

    private static final long serialVersionUID = 8814275991318564432L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="NAME")
    @NotNull(message = "blog.category.name.required")
    private String name;

    @Column(name="CODE")
    @Size(max = 100, message = "blog.category.code.length")
    @NotNull(message = "blog.category.code.required")
    private String code;

    @Column(name="POSITION")
    private int postition;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<PostEntity> posts;

    public Set<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostEntity> posts) {
        this.posts = posts;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPostition() {
        return postition;
    }

    public void setPostition(int postition) {
        this.postition = postition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogCategoryEntity that = (BlogCategoryEntity) o;

        if (id != that.id) return false;
        if (postition != that.postition) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + postition;
        return result;
    }

    @Override
    public String toString() {
        return "BlogCategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", postition=" + postition +
                '}';
    }
}
