package com.example.datastructuresamples;

public class UnionFind {

    //Number of elements in this union find
    private int size;

    //User to track size of each of the components
    private int[] sz;

    //id[i] points to the parent node of each element. id[i] = i means it is a root node.
    private int[] id;

    //Used to track number of components in each union find.
    private int numOfComponents;

    public UnionFind(int size) {
        if (size == 0) throw new IllegalArgumentException("Size <= 0 not allowed");

        this.size = numOfComponents = size;
        sz = new int[size];
        id = new int[size];


        for (int i = 0; i < size; i++) {
            id[i] = i; // Link to itself (self root)
            sz[i] = 1; // each component is originally size one.
        }
    }

    //Given a element, it finds which set it belongs to.
    public int find(int p) {
        int root = p;
        //Find root of the set
        while (root != id[root]) {
            root = id[root];
        }

        //Compress path leading back to the root
        //Doing this operation is called path compression
        //and gives us amortized constant time complexity
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;
    }

    //Return if elements p and q are in the same set.
    public boolean connected (int p, int q) {
        return  find(p) == find(q);
    }

    //Return size of component/set p belongs to
    public int componentSize(int p) {
        return sz[find(p)];
    }

    //Return number of elements in UnionFind/Disjoint set
    public int size() {
        return size;
    }

    //Return number of remaining components/sets
    public int components() {
        return numOfComponents;
    }

    //Unify components/sets containing elements p and q
    public void unify(int p, int q) {
        //First get roots of p and q
        int root1 = find(p);
        int root2 = find(q);

        //If both elements already belong to same group then do nothing
        if (root1 == root2) return;

        //Else we merge the smaller group to the larger group
        if (sz[root1] < sz[root2]) {
            sz[root2] += sz[root1];
            id[root1] = root2;
        } else {
            sz[root1] += sz[root2];
            id[root2] = root1;
        }

        //Since we merged the 2 sets, so number of groups will be reduced by 1
        numOfComponents--;
    }
}