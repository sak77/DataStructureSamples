package com.example.datastructuresamples;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Linked list is a set of nodes which contain data and each node holds reference to another node holding data.
 * All LLs have a head node and a tail node.
 * There are 2 types- Singly Linked Lists and Doubly Linked lists.
 * Singly Linked lists - each node holds data and reference to next node. Hence traversing is possible
 * only in one direction in the list.
 * Doubly Linked lists - each node holds data and reference to previous and next nodes. Hence traversing
 * is possible in both directions.
 * @param <T>
 */
public class MyDoublyLinkedList<T> implements Iterable<T> {

    //class representing node in the linked list
    private class Node<T> {
        private T data;
        private Node<T> previous;
        private Node<T> next;

        Node(T element, Node<T> prev, Node<T> next){
            this.data = element;
            this.previous = prev;
            this.next = next;
        };
    }

    Node<T> head = null;    //By default list is empty
    Node<T> tail = null;
    int linked_list_size = 0;


    //Add node to the start of the list, O(1)
    public void addFirstElement(T element) {
        //if LL is empty then simply create a new node and have it assigned as head and tail and set size to 1
        if (linked_list_size == 0) {
            head = tail = new Node<>(element, null, null);
        } else {
            //new node which holds data element and whose next pointer points to current head
            Node<T> newNode = new Node<>(element, null, head);
            //update current head's previous to refer to the new node
            head.previous = newNode;
            //head = newNode makes head point to newNode. But
            //head's original node is not affected....it still has its original value.
            //So basically head is a pointer to the object.
            //When we assign head to newNode it does not update its values to those of NewNode.
            //Instead it starts pointing to newNode object.
            //The original head node is referenced via newNode's next pointer.
            head = newNode;
        }

        linked_list_size++;
    }

    //Add node to end of list - O(1)
    public void addLastElement(T element) {
        //if LL is empty then simply create a new node and have it assigned as head and tail and set size to 1
        if (linked_list_size == 0) {
            head = tail = new Node<>(element, null, null);
        } else {
            //add new node which holds element data and whose previous pointer refers the current tail node
            Node<T> newNode = new Node<>(element, tail, null);
            //current tail's next pointer refers the new node
            tail.next = newNode;
            //update head to new Node
            tail = newNode;
        }
        linked_list_size++;
    }

    //Add node at specific index - O(n)
    public void addElementAt(T element, int index) {
        //Check if index is within size.
        if (index < linked_list_size) {
            //Set pointer traverse to head
            Node<T> traverse = head;
            //Traverse to the node which points to the index where we wish to add the new element
            for (int i = 0; i < linked_list_size; i++) {
                if (i != index - 1) {
                    traverse = traverse.next;
                } else {
                    //Exit loop
                    break;
                }
            }
            //Now we create a new node with element data and whose previous pointer points to
            // current node referred by traverse and whose next node points to next referred by traverse referred node..
            Node<T> newNode = new Node<>(element, traverse, traverse.next);
            //Now existing traverse node next points to new Node
            traverse.next = newNode;
            //increment size by 1
            linked_list_size++;
        } else {
            //Else throw exception
            throw new IllegalArgumentException("Index is greater than size of linked list");
        }
    }

    //Remove node from start of the list - O(1)
    public void removeItemAtStart() {
        if (linked_list_size == 0) {
            Log.i("TAG", "List is empty");
            return;
        }
        //Assign pointer to the original head
        Node<T> old_head = head;
        //move head to next pointer
        head = head.next;
        //Delete current head
        old_head = null;
        //reduce size by 1
        linked_list_size--;
    }

    //Remove node from end of the list - O(1)
    //However, in case of singly LL, since we have to traverse the full list, it would be O(n)
    public void removeItemAtEnd() {
        if (linked_list_size == 0) {
            Log.i("TAG", "List is empty");
            return;
        }
        //Assign pointer to original tail
        Node<T> original_tail = tail;
        //move tail to previous pointer
        tail = tail.previous;
        //Delete current tail
        original_tail = null;
        linked_list_size--;
    }

    //Find index of node in the list - O(n)
    public int getIndexOf(T element) {
        //Get pointer to head
        Node<T> current_node = head;
        for (int i = 0; i < linked_list_size; i++) {
            if (current_node.data == element) {
              return i;
            }
        }
        return -1;  //Index not found
    }

    //Find node at index - O(n)
    public T getElementAtIndex(int index) {
        //Get pointer to head
        Node<T> current_node = head;
        for (int i = 0; i < linked_list_size; i++) {
            if (i == index) {
                return current_node.data;
            }else {
                current_node = current_node.next;
            }
        }
        return null;    //Index not found
    }

    //Get size of the list
    public int getLinked_list_size() {
        return linked_list_size;
    }

    //Empty the list
    public void clearAll() {
        while (head.next != null) {
            Node<T> current_node = head;
            //Move head to next node
            head = head.next;
            //Delete current node
            current_node = null;
            linked_list_size--;
        }
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
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
