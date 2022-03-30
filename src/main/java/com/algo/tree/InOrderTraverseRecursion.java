package com.algo.tree;

import com.structure.tree.BinaryTree;

import java.util.Arrays;

public class InOrderTraverseRecursion {

    public void inOrder(BinaryTree.Node node) {
        if(node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree(Arrays.asList(1,2,4,null,null,5,null,null,3));
        InOrderTraverseRecursion traverse = new InOrderTraverseRecursion();
        traverse.inOrder(bt.getRoot());
    }
}
