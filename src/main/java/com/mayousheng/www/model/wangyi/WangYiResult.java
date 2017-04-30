package com.mayousheng.www.model.wangyi;

import java.util.List;

/**
 * Created by marking on 2017/4/30.
 */
public class WangYiResult {

    private int size;

    private List<WangYiNews> list;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<WangYiNews> getList() {
        return list;
    }

    public void setList(List<WangYiNews> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "WangYiResult{" +
                "size=" + size +
                ", list=" + list +
                '}';
    }
}
