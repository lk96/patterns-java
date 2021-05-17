package org.kevin.bst;

public class TreeNode<T extends Comparable<T>> {

    private final T val;

    private TreeNode<T> left;

    private TreeNode<T> right;


    public TreeNode(T val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public T getVal() {
        return val;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public void insert(T valToInsert) {
        var parent = getParentNodeOfValueToBeInserted(valToInsert);
        parent.insertNewChild(valToInsert);
    }

    private TreeNode<T> getParentNodeOfValueToBeInserted(T valToInsert) {
        TreeNode<T> parent = null;
        var curr = this;

        while (curr != null) {
            parent = curr;
            curr = curr.traverseOneLevelDown(valToInsert);
        }

        return parent;
    }

    private TreeNode<T> traverseOneLevelDown(T value) {
        if (this.isGreaterThan(value)) {
            return this.left;
        }
        return this.right;
    }

    private void insertNewChild(T valToInsert) {
        if (this.isLessThanOrEqualTo(valToInsert)) {
            this.setRight(new TreeNode<>(valToInsert));
        } else {
            this.setLeft(new TreeNode<>(valToInsert));
        }
    }

    private boolean isGreaterThan(T val) {
        return this.val.compareTo(val) > 0;
    }

    private boolean isLessThanOrEqualTo(T val) {
        return this.val.compareTo(val) < 1;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
