package com.structure.queue;

public class Queue<T>{

    private Node head;
    private Node curr;

    public void enqueue(T val) {
        Node last = curr;
        curr = new Node(val);
        if(head == null) {
            head = curr;
        } else {
            last.next = curr;
        }
    }

    public T dequeue() {
        T val = head.val;
        head = head.next;
        return val;
    }

    private class Node {
        Node next;
        T val;

        Node(T val) {
            this.val = val;
        }

        Node(T val, Node next) {
            this(val);
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.dequeue();
        q.enqueue(5);
    }
}
