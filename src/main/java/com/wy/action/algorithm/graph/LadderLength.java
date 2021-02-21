package com.wy.action.algorithm.graph;

import java.util.*;

public class LadderLength {
    /**
     * 单词接龙的最短长度
     * https://leetcode-cn.com/problems/word-ladder
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() == 0 || !wordList.contains(endWord)) {
            return 0;
        }
        // 记录已经访问过的单词
        Set<String> visited = new HashSet<>();
        Set<String> words = new HashSet<>(wordList);

        // 广度优先遍历
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int len = beginWord.length();
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 每层的遍历
            for(int i=0; i< size; i++) {
                String str = queue.poll();
                char[] charArr = str.toCharArray();
                // 每个字母的替换
                for (int j=0; j<len; j++) {
                    char originChar = charArr[j];
                    for(char c = 'a'; c<='z'; c++) {
                        if (c == charArr[j]) {
                            continue;
                        }
                        charArr[j] = c;
                        String s = new String(charArr);
                        if( endWord.equals(s)) {
                            return step+1;
                        }
                        if (words.contains(s) && !visited.contains(s)) {
                            queue.add(s);
                            visited.add(s);
                        }
                        // 恢复原位
                        charArr[j] =originChar;
                    }
                }

            }

            step++;

        }
        return 0;

    }

    /**
     * 单词接龙2，找出所有最短路径的词
     * https://leetcode-cn.com/problems/word-ladder-ii/
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if (wordList.size() == 0 || !wordList.contains(endWord)) {
            return result;
        }
        // 记录已经访问过的单词
        Set<String> visited = new HashSet<>();
        Set<String> words = new HashSet<>(wordList);

        // 广度优先遍历
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int len = beginWord.length();
        boolean find = false;

        List<String> one = new ArrayList<>();
        one.add(beginWord);

        Map<String, List<String>> map = new HashMap<>();
        map.put(beginWord, one);

        while (!queue.isEmpty()) {
            List<List<String>> resultLevel = new ArrayList<>();
            int size = queue.size();
            // 每层的遍历
            for(int i=0; i< size; i++) {
                String str = queue.poll();
                List<String> list = map.get(str);
                char[] charArr = str.toCharArray();
                // 每个字母的替换
                for (int j=0; j<len; j++) {
                    char originChar = charArr[j];
                    for(char c = 'a'; c<='z'; c++) {
                        if (c == charArr[j]) {
                            continue;
                        }
                        charArr[j] = c;
                        String s = new String(charArr);

                        if (words.contains(s) && !visited.contains(s)) {
                            queue.add(s);
                            visited.add(s);
                            List<String> newList = new ArrayList<>(list);
                            newList.add(s);
                            map.put(s, newList);
                        }
                        if( endWord.equals(s)) {
                            result.add(map.get(s));
                            find = true;
                        }
                        // 恢复原位
                        charArr[j] =originChar;
                    }
                }
                map.remove(str);
            }

            if (find == true) {
                return result;
            }

        }
        return result;

    }
}
