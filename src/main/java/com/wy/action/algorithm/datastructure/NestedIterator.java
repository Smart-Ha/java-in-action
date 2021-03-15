package com.wy.action.algorithm.datastructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {

    private Iterator<Integer> it;

    public NestedIterator(List<NestedInteger> list) {
        List<Integer> res = new LinkedList<>();
        for( NestedInteger node: list) {
            traverse(node, res);
        }
        this.it = it;
    }

    private void traverse(NestedInteger node, List<Integer> res) {
        if (node.isInteger()) {
            res.add(node.getInteger());
            return;
        }
        for(NestedInteger integer: node.getList()) {
            traverse(integer, res);
        }
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public Integer next() {
        return it.next();
    }
}
