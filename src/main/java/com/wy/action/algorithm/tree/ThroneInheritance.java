package com.wy.action.algorithm.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ThroneInheritance {

    public class Node {
        public String name;
        public List<Node> children;
        public boolean alive;
        public Node(String name) {
            this.name = name;
            this.alive = true;
        }
    }

    Map<String, Node> map = new HashMap<>();
    Node root = null;
    public ThroneInheritance(String kingName) {
        root = new Node(kingName);
        map.put(kingName, root);
    }

    public void birth(String parentName, String childName) {
        Node node = map.get(parentName);
        if (node.children == null) {
            node.children = new ArrayList<>();
        }
        Node child = new Node(childName);
        node.children.add(child);
        map.put(childName, child);
    }

    public void death(String name) {
        Node node = map.get(name);
        node.alive = false;
    }

    /**
     * 获取继承权 先序遍历
     * @return
     */
    public List<String> getInheritanceOrder() {
        List<String> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    private void preOrder(Node root, List<String> list) {
        if (root == null) {
            return;
        }
        if (root.alive)
            list.add(root.name);
        if (root.children != null) {
            for(Node node: root.children) {
                preOrder(node, list);
            }
        }
    }
}
