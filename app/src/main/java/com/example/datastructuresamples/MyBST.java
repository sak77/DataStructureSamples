package com.example.datastructuresamples;

import android.icu.text.Edits;

/**
 * BST is a binary tree which satisfies the BST invariant: Left child value is less than node
 * whereas right child value is greater than node.
 *
 * It can have a root node. Each node can have max 2 children.
 *
 * Average complexity of add, remove, search operations is O(log(n)). But in worst case it is O(n).
 *
 * Each element of BST must be comparable so it can be ordered within the BST.
 * BST may have duplicate values.
 *
 *
 */

//BST takes any type which is comparable.
public class MyBST <T extends Comparable<T>> {

    //2 variables - first to track root node and second to have size of the tree.
    private int nodeCount = 0;

    private Node rootNode = null;

    //Private Node class which has value, ref to left child, ref to right child
    private class Node {
        T data; //Value stored in node class is of type comparable
        Node left, right;
        public Node(Node left, Node right, T element) {
            this.data = element;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return nodeCount;
    }

    //Add element to the BST
    public void add(T newElement) {
        //We can ignore this element if it already exists, since here we dont want duplicates
        if (contains(newElement)) {
            return;
        }

        rootNode = add(rootNode, newElement);
        nodeCount++;
    }

    //Recursive logic to add node
    private Node add(Node root, T newElement) {
        //If BST is empty or we reach a leaf node, simply assing this element value as the root node
        if (root == null) {
            root = new Node(null, null, newElement);
        } else {
            //compare value with root and iterate...
            if (root.data.compareTo(newElement) < 0) {
                //element is smaller than root. So check left sub-tree
                add(root.left, newElement);
            } else {
                //else check right sub-tree
                add(root.right, newElement);
            }
        }
        return root;
    }

    private boolean contains(T element) {
        //Traverse BST to check if element already exists
    }


    public void removeNode(T element) {
        //If node does not exist then simply return
        if (!contains(element)) {
            return;
        }

        removeNode(rootNode, element);
        nodeCount--;
    }

    private void removeNode(Node root, T element) {
        //if BST is empty or we reach the leaf node without finding the element then simply return
        if (root == null) {
            return;
        }

        //2 steps to remove a node - Find and Replace.
        //First find the node position
        if (root.data.compareTo(element) < 0) {
            //value is less than node so dig left..
            removeNode(root.left, element);
        } else if (root.data.compareTo(element) > 0){
            //dig right
            removeNode(root.right, element);
        } else {
            //We found the element...so we proceed to Step 2. Replace it
            //Replace has 4 possible scenarios.
            //We replace a leaf node.
            if (root.left == null && root.right == null) {
                root = null;    //Destroy reference to current node. And do nothing else.
            } else if (root.left == null) { //node has right child only..we swap node to delete with its right child
                root.data = null;
                root = null;
                root = root.right;
            } else if (root.right == null) { //node only has left child, so we replace it with its left child
                root.data = null;
                root = null;
                root = root.left;
            } else {
                //Here node can be replaced either by the largest element in its left sub tree or
                //the smallest element from the right sub-tree.

                //Find leftmost child in right sub-tree
                Node temp = digLeft(root.right);

                //Swap data this with current root data
                root.data = temp.data;

                //Now go into right sub tree and remove the leftmost node we found and swapped. This
                //prevents the tree from having 2 nodes with the same value.
                removeNode(root.right, temp.data);
            }
        }

    }

    //Method digs to left most child of the current node
    private Node digLeft(Node currentNode) {
        while (currentNode.left!= null) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }

    enum TreeTraversalOrder {
        PRE_ORDER, IN_ORDER, POST_ORDER, LEVEL_ORDER
    }

    public java.util.Iterator<T> traverse(TreeTraversalOrder traversalOrder) {
        //Pre-order, in-order, post-order, level order traversals...
        switch (traversalOrder) {

        }
    }
}
