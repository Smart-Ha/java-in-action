package com.wy.action.algorithm.datastructure;

import org.junit.Assert;
import org.junit.Test;

public class Trie {
    private Entry[] data;
    public class Entry {
        boolean isLeaf;//是否是结束结节点
        char val;
        Entry[] children;
        public Entry(boolean isLeaf, char val) {
            this.isLeaf = isLeaf;
            this.val = val;
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        data = new Entry[26];
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Entry[] list = data;
        for(int i=0; i< word.length(); i++) {
            char c = word.charAt(i);
            Entry entry = list[c-'a'];
            if (entry == null) {
                entry = new Entry(false, c);
                list[c-'a'] = entry;
                entry.children = new Entry[26];
            }
            list = entry.children;
            if (i == word.length()-1) {
                entry.isLeaf = true;
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Entry[] list = data;
        for(int i=0; i< word.length(); i++) {
            if (list == null) {
                return false;
            }
            char c = word.charAt(i);
            Entry entry = list[c-'a'];
            if (entry == null) {
                return false;
            }
            list = entry.children;
            if (i == word.length()-1) {
                return entry.isLeaf ;
            }
        }
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Entry[] list = data;
        for(int i=0; i< prefix.length(); i++) {
            if (list == null) {
                return false;
            }
            char c = prefix.charAt(i);
            Entry entry = list[c-'a'];
            if (entry == null) {
                return false;
            }
            list = entry.children;
        }
        return true;
    }

    @Test
    public void test1()  {
        Trie trie = new Trie();

        trie.insert("apple");
        Assert.assertEquals(true,trie.search("apple"));   // 返回 true
        Assert.assertEquals(false,trie.search("app"));     // 返回 false
        Assert.assertEquals(true, trie.startsWith("app")); // 返回 true
        trie.insert("app");
        Assert.assertEquals(true, trie.search("app"));     // 返回 true
    }
}
