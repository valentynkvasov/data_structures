package com.algo.tree;

import com.structure.tree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PreOrderTraverseIterative {

    //dfs
    public void preOrderDfs(BinaryTree.Node node) {
        Stack<BinaryTree.Node> stack = new Stack<>();
        stack.add(node);

        while (!stack.isEmpty()) {
            BinaryTree.Node pop = stack.pop();
            System.out.println(pop.data);

            if(pop.right != null) {
                stack.add(pop.right);
            }
            if(pop.left != null) {
                stack.add(pop.left);
            }
        }
    }

    //bfs
    public void preOrderBfs(BinaryTree.Node node) {
        Queue<BinaryTree.Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            BinaryTree.Node poll = queue.poll();
            System.out.println(poll.data);

            if(poll.left != null) {
                queue.add(poll.left);
            }
            if(poll.right != null) {
                queue.add(poll.right);
            }
        }
    }
}
