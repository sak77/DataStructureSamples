package com.example.datastructuresamples;


import android.app.VoiceInteractor;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * A PQ is an ADT which is a queue where each node has a priority.
 * Higher priority elements are removed first from the queue.
 * PQ always consists of elements which are comparable to each other.
 * PQs are implemented using Heap DS. But they both are not similar.
 * Heap DS is a tree like DS where each node is related to its parent/child via some logic.
 * This logic is called the heap invariance or heap property.
 * There are 2 types of heap, Min heap and Max heap.
 * Min heap invariance is where each parent node has lesser value than its child node.
 * Max is opposite of min heap.
 * Binary Heap is a heap where each node can have max 2 children.
 * Complete Binary Heap is where each node will have 2 children except maybe the leave nodes.
 * Operations possible on heap - Add new item O(log(n)), remove root O(log(n)), peek root O(1), remove item naively O(n),
 * remove item using hash tables O(log(n)), contains item naively O(n), check contains via hash table O(1)
 * Heap has a root item which is the item at index 0. It has highest priority in the heap.
 * For a given node at index i in a 0 based Heap. Its left child is as 2i + 1 and right child is at 2i + 2.
 * Below we implement a PQ using a List.
 */
public class MyPriorityQueue<T extends Comparable<T>> {

    //Number of elements currently in the heap
    private int heapSize = 0;

    //Internal capacity of the heap
    private int heapCapacity = 0;

    //Dynamic list to track elements in the heap
    private List<T> heap;

    /*
        This map allows us to map each node value in the heap with possible indices where the
        node value exists. It allows us to have O(log(n)) removals and O(1) containment check at
        cost of some additional space and minor overhead
     */
    Map<T, TreeSet<Integer>> map = new HashMap<>();

    //Construct a PQ with initial size
    MyPriorityQueue(int size) {
        heap = new ArrayList<>(size);
    }

    //Construct a PQ with given elements
    MyPriorityQueue(T[] elements) {
        heapSize = heapCapacity = elements.length;
        heap = new ArrayList<>(heapCapacity);

        //place all elements in the heap
        for (int i = 0; i < heapSize; i++) {
            mapAdd(elements[i], i);
            heap.add(elements[i]);
        }

        //Heapify process, O(n)
        for (int i = Math.max(0, (heapSize / 2 - 1)); i > 0; i--) {
            sink(i);
        }
    }

    //Priority Queue construction O(log(n))
    MyPriorityQueue(Collection<T> elements) {
        this(elements.size());
        for (T element : elements) add(element);
    }


    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void clear() {
        for (int i = 0; i < heapSize; i++) {
            heap.set(i, null);
        }
        heapSize = 0;
        map.clear();
    }

    public int size() {
        return heapSize;
    }

    //Returns element with lowest priority in PQ. If PQ is empty then returns null, O(1)
    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    //Remove item at root of the heap O(log(n))
    public T poll() {
        return removeAt(0);
    }

    //Using hashtable this function has complexity O(1)
    //Otherwise you need to perform a linear scan where the complexity becomes O(n)
    public boolean contains(T element) {
        return map.containsKey(element);
    }

    //Add element to the queue. The element cannot be null. O(log(n))
    public void add(T element) {
        if (element == null) throw new IllegalArgumentException("Null element not allowed");

        if (heapSize < heapCapacity) {
            heap.set(heapSize, element);
        } else {
            heap.add(element);
            heapCapacity++;
        }

        mapAdd(element, heapSize);
        swim(heapSize);
        heapSize++;
    }

    //Helper method to check if node i is less than node j
    public boolean less(int i, int j) {
        T nodei = heap.get(i);
        T nodej = heap.get(j);
        //Using compraeTo method from comparable interface to match the 2 nodes.
        return nodei.compareTo(nodej) <= 0;
    }

    //Swim up an element in the tree. O(log(n))
    private void swim(int pos) {
        //Get element at pos
        T element = heap.get(pos);

        //left child = 2*parent pos + 1
        //right child = 2*parent pos + 2
        int parent = (pos - 1) / 2;

        //Match parent and current node.
        //assuming min heap. So if parent is greater than current node then swap the two
        while (pos > 0 && less(pos, parent)) {
            //swap(parent, pos);
            //update indices of parent and current node and also map
            pos = parent;
            parent = (pos - 1) / 2;
            //continue again until heap invariant is not satisfied
        }
    }

    //Top down sink of element, O(log(n))
    public void sink(int pos) {

        //Compare parent with smaller child. Assuming Min heap.
        while (true) {
            //Left child and right child pos
            int left = 2 * pos + 1;
            int right = 2 * pos + 2;
            int smallerchild = left;    //assume left child is smaller by default

            //Check our assumption
            if (right < heapSize && !less(left, right)) {
                smallerchild = right;
            }

            //Stop if we are outside bound of the heap or we cannot sink further
            if (smallerchild >= heapSize || less(pos, smallerchild)) break;
                //if parent is greater than smaller child then swap
                //swap(smallerchild, parent);
                //update pos
                pos = smallerchild;
        }
    }

    //Swap 2 nodes assuming i and j are valid. O(1)
    public void swap(int i, int j) {
        T i_element = heap.get(i);
        T j_element = heap.get(j);

        heap.set(i, j_element);
        heap.set(j, i_element);

        //Update hash table
        mapSwap(i_element, j_element, i, j);
    }


    //Removes particular element, O(log(n))
    public boolean remove(T element) {
        //Naive approach takes linear time, O(n)
        /*for (int i = 0; i < heapSize; i++) {
            if (element.equals(heap.get(i))) {
                removeAt(i);
                return true;
            }
        }*/
        //Logarithmic removal with hash tables
        Integer index = mapGet(element);
        if (index != null) removeAt(index);
        return index != null;
    }

    //Remove item at index O(log(n))
    private T removeAt(int index) {
        if (isEmpty()) return null;

        heapSize--;
        //Swap item with item at last index
        T removed_data = heap.get(index);
        swap(index, heapSize);
        //Now remove item from heap
        heap.set(heapSize, null);
        mapRemove(removed_data, heapSize);

        //suppose we removed last data from the heap then simply return the removed_data
        if (index == heapSize) return removed_data;

        //Sink/swim the swapped item
        T element = heap.get(index);

        sink(index);

        //if sinking did not work then try swim
        if (heap.get(index).equals(element)) {
            swim(index);
        }

        return removed_data;
    }

    //Map helper method to add new item to the hash table
    private void mapAdd(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        //New value being inserted to map
        if (set == null) {
            set = new TreeSet<>();
            set.add(index);
            map.put(value, set);
        } else set.add(index);
    }

    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        set.remove(index);  //Treesets take O(log(n)) time
        if (set.size() == 0) map.remove(value);
    }

    private Integer mapGet(T value) {
        TreeSet<Integer> set = map.get(value);
        if (set != null) return set.last();
        return null;
    }

    private void mapSwap(T val1, T val2, int val1index, int val2index) {
        TreeSet<Integer> set1 = map.get(val1);
        TreeSet<Integer> set2 = map.get(val2);

        set1.remove(val1index);
        set1.add(val2index);

        set2.remove(val2index);
        set2.add(val1index);
    }

    @NonNull
    @Override
    public String toString() {
        return heap.toString();
    }
}