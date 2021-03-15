package com.wy.action.algorithm.datastructure;

import java.util.Iterator;
import java.util.LinkedList;

public class MyHashMap {

    private final static int LEN = 717;

    private LinkedList<Entry>[] data;

    protected class Entry {
        int key;
        int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    /** Initialize your data structure here. */
    public MyHashMap() {
        data = new LinkedList[LEN];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = key % LEN;
        LinkedList<Entry> list = data[hash];
        if (list == null) {
            list = new LinkedList<>();
            list.add(new Entry(key, value));
            data[hash] = list;
            return;
        }
        Iterator<Entry> iterator = list.iterator();
        while (iterator.hasNext()) {
            Entry entry  = iterator.next();
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }
        list.add(new Entry(key, value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = key % LEN;
        LinkedList<Entry> list = data[hash];
        if (list == null) {
            return -1;
        }
        Iterator<Entry> iterator = list.iterator();
        while (iterator.hasNext()) {
            Entry entry  = iterator.next();
            if (entry.key == key) {
                return entry.value;
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = key % LEN;
        LinkedList<Entry> list = data[hash];
        if (list == null) {
            return ;
        }
        Iterator<Entry> iterator = list.iterator();
        while (iterator.hasNext()) {
            Entry entry  = iterator.next();
            if (entry.key == key) {
                iterator.remove();
                return;
            }
        }
    }
}
