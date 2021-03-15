package com.wy.action.algorithm.datastructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NestedIterator2 implements Iterator<Integer> {

    private LinkedList<NestedInteger> list;

    public NestedIterator2(List<NestedInteger> list) {
        this.list = new LinkedList<>(list);
    }

    @Override
    public boolean hasNext() {
        while (!list.isEmpty() && !list.get(0).isInteger()) {
            List<NestedInteger> nestedInteger = list.remove(0).getList();
            for (NestedInteger integer: nestedInteger) {
                list.addFirst(integer);
            }
        }
        return !list.isEmpty();
    }

    @Override
    public Integer next() {
        return list.removeLast().getInteger();
    }
}
