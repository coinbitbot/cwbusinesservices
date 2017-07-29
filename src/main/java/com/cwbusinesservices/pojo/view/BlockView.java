package com.cwbusinesservices.pojo.view;

import com.cwbusinesservices.pojo.helpers.CompareIntegerId;
import com.cwbusinesservices.pojo.helpers.GetableById;
import com.cwbusinesservices.pojo.enums.BlockCodesEnum;

import java.io.Serializable;

/**
 * Created by Andrii on 28.07.2017.
 */
public class BlockView extends CompareIntegerId implements Serializable,GetableById<Integer> {

    private static final long serialVersionUID = -3357539158595638456L;

    private Integer id;
    private BlockCodesEnum code;
    private String title;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

        BlockView blockView = (BlockView) o;

        if (id != null ? !id.equals(blockView.id) : blockView.id != null) return false;
        if (code != blockView.code) return false;
        return title != null ? title.equals(blockView.title) : blockView.title == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlockView{" +
                "id=" + id +
                ", code=" + code +
                ", title='" + title + '\'' +
                '}';
    }
}
