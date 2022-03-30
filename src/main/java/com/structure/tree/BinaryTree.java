package com.structure.tree;

import java.util.*;

public class BinaryTree {

    private Node root;

    public BinaryTree(List<Integer> list) {
        root = buildBinaryTree(new LinkedList<>(list));
    }

    private Node buildBinaryTree(Queue<Integer> q) {
        if(q.isEmpty()) {
            return null;
        }
        if(q.peek() == null) {
            q.poll();
            return null;
        }
        Node root = new Node(q.poll());
        root.left = buildBinaryTree(q);
        root.right = buildBinaryTree(q);
        return root;
    }

    public static class Node {
        public Node left;
        public Node right;
        public int data;

        Node(int data) {
            this.data = data;
        }
    }

    public Node getRoot() {
        return root;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree(Arrays.asList(1,2,4,null,5,null,null,3));
    }
}
