package com.wy.action.algorithm.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 拓扑图
 * @Author wangyong
 * @Date 2019-12-17
 */
public class Graph {
    private int v;//顶点的个数
    private Vertex[] vertices;
    private Map<String, Integer> v2Idx;
    public Graph(List<String> vic) {
        this.v = vic.size();
        vertices = new Vertex[this.v];
        v2Idx = new HashMap<>();
        for (int i=0; i<vic.size(); i++) {
            v2Idx.put(vic.get(i), i);
            vertices[i] = new Vertex(vic.get(i), new LinkedList<String>());
        }
    }

    public static class Vertex {
        private String name;
        private LinkedList<String> adj;//邻接表


        public Vertex( String name, LinkedList<String> adj) {
            this.adj = adj;
            this.name = name;
        }
        public void add(String s) {
            adj.add(s);
        }

        public String getName() {
            return name;
        }

        public LinkedList<String> getAdj() {
            return adj;
        }
    }

    public int getV() {
        return v;
    }

    /**
     * s 先于t s->t
     * @param s
     * @param t
     */
    public void addEdge(String s, String t) {
        int index = v2Idx.get(s);
        vertices[index].add(t);
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public LinkedList<String> getVAdj(String v) {
        int index = v2Idx.get(v);
        return vertices[index].getAdj();
    }
}
