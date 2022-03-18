package com.structure.stack;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayStack<T> {

    private Object[] objects;
    private int size;

    public ArrayStack(int initialCapacity) {
        objects = new Object[initialCapacity];
        size = 0;
    }

    public void push(T element) {
        if (size == objects.length - 1) {
            grow();
        }
        size ++;
        objects[size++] = element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return (T) objects[size - 1];
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return (T) objects[--size];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        Object[] newArray = new Object[size * 2];
        System.arraycopy(objects, 0, newArray, 0, size);
        objects = newArray;
    }
}
