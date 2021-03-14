package com.wy.action.algorithm.datastructure;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashSet {

    private final int BASE = 811;
    private LinkedList[]  data;
    /** Initialize your data structure here. */
    public MyHashSet() {
        data = new LinkedList[BASE];
//        for(int i=0; i<BASE; i++) {
//            data[i] = new LinkedList();
//        }
    }

    public void add(int key) {
        int hash = key % BASE;
        LinkedList<Integer> list = data[hash];
        if (list == null) {
            data[hash] = new LinkedList();
        }
        data[hash].add(key);


    }

    public void remove(int key) {
        int hash = key % BASE;
        LinkedList<Integer> list = data[hash];
        if (list == null) {
            return;
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == key) {
                iterator.remove();
                return;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = key % BASE;
        LinkedList<Integer> list = data[hash];
        if (list == null) {
            return false;
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == key) {
                return true;
            }
        }
        return false;
    }
}
