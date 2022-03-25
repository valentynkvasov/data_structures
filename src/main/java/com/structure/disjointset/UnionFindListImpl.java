package com.structure.disjointset;

import java.util.HashMap;
import java.util.Map;

public class UnionFindListImpl {

    private Map<Integer, Node> map;

    class Node {
        Node parent;
        int rank;
        int value;
    }

    public UnionFindListImpl() {
        map = new HashMap<>();
    }

    public void makeSet(int value) {
        Node node = new Node();
        node.parent = node;
        node.rank = 0;
        node.value = value;
        map.put(value, node);
    }

    public boolean union(int val1, int val2) {
        Node p = map.get(val1);
        Node q = map.get(val2);

        Node pParent = findRootOfSet(p);
        Node qParent = findRootOfSet(q);

        if(pParent.value == qParent.value) {
            return false;
        }

        if(pParent.rank >= qParent.rank) {
            pParent.rank = pParent.rank == qParent.rank ? pParent.rank + 1 : pParent.rank;
            qParent.parent = pParent;
        } else {
            pParent.parent = qParent;
        }

        return true;
    }

    public Node findRootOfSet(int val) {
        return findRootOfSet(map.get(val));
    }

    public Node findRootOfSet(Node node) {
        Node parent = node.parent;
        if(parent == node) {
            return parent;
        }
        node.parent = findRootOfSet(node.parent);
        return node.parent;
    }

    public static void main(String[] args) {
        UnionFindListImpl union = new UnionFindListImpl();
        union.makeSet(1);
        union.makeSet(2);
        union.makeSet(3);
        union.makeSet(4);
        union.makeSet(5);
        union.makeSet(6);

        union.union(1,2);

        union.union(3,4);
        union.union(4,5);
        union.union(5,6);

        System.out.println(union.findRootOfSet(1).rank);
        System.out.println(union.findRootOfSet(3).rank);
        System.out.println(union.findRootOfSet(6).rank);
    }
}
