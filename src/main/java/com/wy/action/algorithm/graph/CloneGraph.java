package com.wy.action.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wangyong
 * @Date 2021-02-24
 */
public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    Map<Node, Node> visited = new HashMap<>();

    /**
     * 克隆图
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node newOne = new Node(node.val);
        visited.put(node, newOne);
        if (node.neighbors == null) {
            return newOne;
        }
        ArrayList<Node> neighbors = new ArrayList<>();
        newOne.neighbors = neighbors;
        for(Node neighbor: node.neighbors) {
            neighbors.add(cloneGraph(neighbor));
        }
        return newOne;

    }


}
