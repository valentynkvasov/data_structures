package com.algo.tree;

import com.structure.tree.BinaryTree;

import java.util.Arrays;

public class PostOrderTraverseRecursion {

    public void postOrder(BinaryTree.Node node) {
        if(node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree(Arrays.asList(1,2,4,null,null,5,null,null,3));
        PostOrderTraverseRecursion traverse = new PostOrderTraverseRecursion();
        traverse.postOrder(bt.getRoot());
    }
}
