package com.example.datastructuresamples;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * A stack is a one ended data structure which models real world stack of items.
 * <p>
 * It allows push and pop operations. It contains a top pointer that holds reference
 * to top of the stack. It follows LIFO way of working with elements.
 *
 * Below i have implemented Stack using arrays. But i can also be implemented using SLL or DLL.
 */
public class MyStack<T> implements Iterable<T> {

    //Top pointer
    int top = -1;
    int stack_size;

    public MyStack(int size) {
        stack_size = size;
    }

    //Using arrays to implement stack
    Object[] array = new Object[stack_size];

    //Push item to stack O(1)
    public void push(T new_item) {
        //if available then
        if (top < array.length - 1) {
            //update top
            top++;
            //Add new item to last available index of stack
            array[top] = new_item;
        } else {
            //else throw error
            throw new IndexOutOfBoundsException("Stack is full.");
        }
    }

    //Pop item from stack - O(1)
    public T pop() {
        //Get element at top
        Object topObject = array[top];
        //Remove top element from array
        array[top] = null;
        //Decrement top by 1
        top--;
        //return topObject
        return (T) topObject;
    }

    public int getStackSize() {
        return top + 1;
    }

    public T peekTop() {
        return (T) array[top];
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return top < array.length - 1;
            }

            @Override
            public T next() {
                return (T) array[top];
            }
        };
    }

    @Override
    public void forEach(@NonNull Consumer<? super T> action) {

    }

    @NonNull
    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
