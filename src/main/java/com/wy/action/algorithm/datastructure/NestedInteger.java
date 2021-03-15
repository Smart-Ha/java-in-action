package com.wy.action.algorithm.datastructure;


import java.util.List;

/**
 *
 */
public class NestedInteger {
    private Integer val;
    private List<NestedInteger> list;

    public NestedInteger(Integer val) {
        this.val = val;
        this.list = null;
    }

    public NestedInteger(List<NestedInteger> list) {
        this.val = null;
        this.list = list;
    }

    public boolean isInteger() {
        return val!=null;
    }
    public Integer getInteger() {
        return val;
    }
    public List<NestedInteger> getList() {
        return list;
    }
}
