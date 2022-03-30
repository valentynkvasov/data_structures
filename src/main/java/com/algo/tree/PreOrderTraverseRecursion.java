package com.algo.tree;

import com.structure.tree.BinaryTree;

import java.util.Arrays;

public class PreOrderTraverseRecursion {

    public void preOrder(BinaryTree.Node node) {
        if(node == null) {
            return;
        }

        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree(Arrays.asList(1,2,4,null,null,5,null,null,3));
        PreOrderTraverseRecursion traverse = new PreOrderTraverseRecursion();
        traverse.preOrder(bt.getRoot());
    }
}
