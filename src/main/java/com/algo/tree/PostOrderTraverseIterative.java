package com.algo.tree;

import com.structure.tree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PostOrderTraverseIterative {

    //dfs
    public void postOrderDfs(BinaryTree.Node node) {
        Stack<BinaryTree.Node> stack = new Stack<>();
        stack.add(node);

        while (stack != null) {
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
    public void postOrderBfs(BinaryTree.Node node) {
        Queue<BinaryTree.Node> queue = new LinkedList<>();
        queue.add(node);

        while (queue != null) {
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
