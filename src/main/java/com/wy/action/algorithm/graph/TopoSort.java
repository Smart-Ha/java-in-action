package com.wy.action.algorithm.graph;

import org.apache.commons.collections4.MapUtils;
import org.junit.Test;

import java.util.*;

/**
 * @Author wangyong
 * @Date 2019-12-17
 */
public class TopoSort {

    /**
     * 如何确定代码源文件的编译依赖关系？
     * 那编译器又该如何通过源文件两两之间的局部依赖关系，确定一个全局的编译顺序呢？
     * @return
     */
    public List<String> getBuildSort(Graph graph) {
        List<String> result = new ArrayList<>();
        //统计每个顶点的入度数
        Map<String, Integer> state = new HashMap<>();
        for (int i=0; i<graph.getV(); i++) {
            Graph.Vertex vertex = graph.getVertices()[i];
            state.put(vertex.getName(), 0);
            for (int j=0; j<vertex.getAdj().size();j++) {
                String v = vertex.getAdj().get(j);
                int count = MapUtils.getIntValue(state, v, 0);
                state.put(v, count+1);
            }
        }
        LinkedList<String> queue = new LinkedList<>();
        // 将入度为0 的梳理数出来，可以先编译
        for(Map.Entry<String, Integer> entry : state.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            String v = queue.remove();
            result.add(v);
            LinkedList<String> adj = graph.getVAdj(v);
            for (int i=0; i< adj.size();i++) {
                String v2 = adj.get(i);
                state.put(v, state.get(v)-1);//入度-1
                if (state.get(v) == 0 ) {//减为0是，说明他依赖的都已经编译完了
                    queue.add(v2);
                }
            }
        }

        return result;
    }


    /**
     *  a-> b  b依赖a
     *  a-> c
     *  a-> e
     *  b-> d
     *  c-> e
     *  d -> e
     */

    @Test
    public void test() {
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        String e = "e";
        List<String> vectors = Arrays.asList("a","b", "c","d", "e");
        Graph graph  = new Graph(vectors);
        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(a, e);
        graph.addEdge(b, d);
        graph.addEdge(c, e);
        graph.addEdge(d, e);
        System.out.println(getBuildSort(graph));
    }
}
