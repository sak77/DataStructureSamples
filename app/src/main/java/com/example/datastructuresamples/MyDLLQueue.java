package com.example.datastructuresamples;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Queue ADT models a real life queue where items are added at end of queue and removed from front of queue (FIFO).
 *
 * New items can be enqueued - added to back of the queue
 * Items can be dequeued - removed from front of the queue
 *
 * It can be implemented using Arrays, SLL or DLL. Since Java provides DLL implementation
 * Out of the box, i will be using java.util.LinkedList to implement my queue here.
 *
 * Note to use SLL to implement a queue, you need to use 2 pointers head and tail as well.
 *
 * Arrays are probably faster way to implement queue. But need to check..
 */
public class MyDLLQueue<T> implements Iterable<T> {

    //Head and tail pointers
    //private int head, tail;

    //Java implementation of DLL
    java.util.LinkedList<T> myDll = new LinkedList<>();

    //Enqueue operation O(1)
    public void enqueue(T newItem) {
        myDll.addLast(newItem);
    }

    //Dequeue operation O(1)
    public T dequeue() throws Throwable {
        if (!myDll.isEmpty()) {
            return myDll.removeFirst();
        }
        //Throw exception here....
        throw new Throwable("List is empty");
    }

    //Get Queue size O(1)
    public int getQueueSize() {
        return myDll.size();
    }

    //Find Item in Queue O(n)
    public boolean queueContains(T searchItem) {
        return myDll.contains(searchItem);
    }

    //Peek first O(1)
    public T peekFirst() {
        if (myDll.isEmpty()){
            throw new RuntimeException("Queue empty");
        }
        return myDll.peekFirst();
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return myDll.iterator();
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
