package com.example.datastructuresamples;


import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Dynamic arrays are arrays whose size is dynamic.
 * So if the number of elements in the array increases beyond its original size,
 * then the array will expand to accommodate more elements.
 * Implement dynamic array using static array.
 */

//Implementing Iterable interface allows an object to be the target of the "for-each loop" statement.
public class MyDynamicArray<T> implements Iterable<T> {

    private int array_size = 4;
    int last_item_index = -1;

    //Internal static array
    private Object[] array = new Object[array_size];

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return last_item_index < array_size;
            }

            @Override
            public T next() {
                return (T) array[last_item_index + 1];
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

    //Add elements
    public void addElement(T newElement) {

        //Check index of last populated item in the array
        if (last_item_index < array.length -1) {
            //if index is less than size of array then go ahead and insert item
            array[last_item_index + 1] = newElement;
            last_item_index++;
        } else {
            //if insert is equal to size of array, then create new array with a larger size (double in this case)
            array_size = array_size * 2;
            Object[] newArray = new Object[array_size];
            //then copy elements from old array to new array and append this item at end of this array
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[last_item_index + 1] = newElement;
            last_item_index++;
            //Update existing array
            array = newArray;
        }
    }

    //Remove element at index
    public void deleteElementAt(int index) {
        //Create a temp array with all values from array except the one at given index
        //for this the temp array size is one less than current array size
        Object[] tempArray = new Object[array_size -1];
        for (int i = 0; i < last_item_index; i++) {
            if (i < index) {
                tempArray[i] = array[i];
            } else {
                tempArray[i] = array[i+1];
            }
        }

        //Now update current array
        array = tempArray;
        //also last item index reduces by 1
        last_item_index--;
    }

    //Get element at index
    public T getElementAt(int index) {
        return (T) array[index];
    }

    //Get index of element
    public int getIndexOfElement(T item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        //Item not found
        return -1;
    }

    public boolean contains(T item) {
        return getIndexOfElement(item) != -1;
    }

    //Clear all elements in array
    public void clearAll() {
        //TODO add implementation
    }

    public int length() {
        return array.length;
    }
}
