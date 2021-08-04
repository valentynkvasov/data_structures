package com.structure.stack;

import java.util.EmptyStackException;

public class LinkedStack<T> {

    private Node stack;
    private int size;

    LinkedStack() {
        size = 0;
        stack = null;
    }

    public void push(T element) {
        Node old = stack;
        stack = new Node(element, old);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        T item = stack.data;
        stack = stack.next;
        size--;

        return item;
    }

    private class Node {
        T data;
        Node next;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
