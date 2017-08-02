package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrii on 02.08.2017.
 */
public class BlogCategoryView extends CompareIntegerId implements Serializable, GetableById<Integer> {

    private static final long serialVersionUID = 4175299988759795361L;
    private Integer id;
    private String name;
    private String code;
    private Integer position;
    private List<Integer> posts;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<Integer> getPosts() {
        return posts;
    }

    public void setPosts(List<Integer> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogCategoryView postView = (BlogCategoryView) o;

        if (id != null ? !id.equals(postView.id) : postView.id != null) return false;
        if (name != null ? !name.equals(postView.name) : postView.name != null) return false;
        if (code != null ? !code.equals(postView.code) : postView.code != null) return false;
        if (position != null ? !position.equals(postView.position) : postView.position != null) return false;
        return posts != null ? posts.equals(postView.posts) : postView.posts == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (posts != null ? posts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", position=" + position +
                ", posts=" + posts +
                '}';
    }
}
