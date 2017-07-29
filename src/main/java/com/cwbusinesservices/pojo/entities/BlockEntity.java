package com.cwbusinesservices.pojo.entities;

import com.cwbusinesservices.pojo.enums.BlockCodesEnum;
import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Andrii on 28.07.2017.
 */
@Entity
@Table(name="BLOCKS")
public class BlockEntity extends CompareIntegerId implements Serializable, GetableById<Integer> {

    private static final long serialVersionUID = -8677583551496421775L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name="CODE")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "block.code.required")
    private BlockCodesEnum code;

    @Column(name="TITLE")
    private String title;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BlockCodesEnum getCode() {
        return code;
    }

    public void setCode(BlockCodesEnum code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockEntity that = (BlockEntity) o;

        if (id != that.id) return false;
        if (code != that.code) return false;
        return title != null ? title.equals(that.title) : that.title == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlockEntity{" +
                "id=" + id +
                ", code=" + code +
                ", title='" + title + '\'' +
                '}';
    }
}
