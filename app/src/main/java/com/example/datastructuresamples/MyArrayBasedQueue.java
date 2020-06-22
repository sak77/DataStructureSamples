package com.example.datastructuresamples;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * First refer MyDLLQueue class.
 * <p>
 * This is a homework to implement a Queue ADT using a static array.
 */
public class MyArrayBasedQueue<T> implements Iterable<T> {

    //Head and tail pointers
    int head = -1;
    int tail = -1;

    //Array
    Object[] array;

    MyArrayBasedQueue(int size) {
        array = new Object[size];   //Create new array of the provided size
    }

    //Enqueue
    public void enqueue(T newItem) {
        //if queue is empty simply add new item and update head and tail
        if (tail <= 0) {
            array[0] = newItem;
            head++;
            tail++;
        } else {
            //if last item is within array size then add new item and update last item index
            if (tail < array.length -1) {
               array[tail + 1] = newItem;
               tail++;
            } else {
                //Queue is full....
                throw new RuntimeException("Queue is full");
            }
        }
    }

    //Dequeue
    public T dequeue() {
        //Check if queue is not empty
        if (array.length == 0 || head == -1) {
            throw new RuntimeException("Queue is empty");
        }
        else {
            //if not then move head to next item while remove reference at head node
            //return old head
            T currentHead = (T) array[head];
            //remove reference of current head
            array[head] = null;
            //Check possibility to move head forward
            if (head < array.length - 1) {
                //Update head
                head++;
            } else {
                //if not possible means queue is maybe empty so reset
                head = -1;
                tail = -1;
            }
            return currentHead;
        }
    }

    //Get size
    public int getQueueSize() {
        return tail + 1;
    }

    //Peek Head
    public T peekHead() {
        if (array.length == 0) {
            throw new RuntimeException("Queue is empty");
        }
        return (T) array[head];
    }

    //Is Empty
    public boolean isEmpty() {
        return array.length == 0;
    }

    //Contains
    public boolean contains(T findItem) {
        for (Object o : array) {
            if (o == findItem) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return tail < array.length;
            }

            @Override
            public T next() {
                return null;
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
