package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private StringBuilder queue;

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void put(K key, V value) {
        validateParams(key, value);
        root = put(root, key, value);
        root.setColor(BLACK);
    }
    
    public void deleteMax() {
        if (root == null) {
            return;
        }

        root = deleteMax(root);

        if (root != null) {
            root.setColor(BLACK);
        }
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == null ? false : node.isRed();
    }

        
    private Node<K, V> deleteMax(Node<K, V> node) {
        if (isRed(node.getLeft())) {
            node = rotateRight(node);
        }

        if (node.getRight() == null) {
            return null;
        }

        if (!isRed(node.getRight()) && !isRed(node.getRight().getLeft())) {
            node = reorganizeRedToRight(node);
        }

        Node<K, V> deleteResult = deleteMax(node.getRight());
        node.setRight(deleteResult);

        return reorganizeTree(node);
    }
    
        private Node<K, V> reorganizeRedToRight(Node<K, V> node) {
        changeColors(node);

        if (isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
            changeColors(node);
        }

        return node;
    }

    private void stringBuilder(Node<K, V> node){
        queue.append(node.getKey().toString()).append(':').append(node.getValue().toString()).append(' ');
    }

    public String getPreOrder(){
        if(root == null){
            throw new NoSuchElementException("Tree is empty");
        }
        queue = new StringBuilder();
        Node<K, V> startNode = root;
        preOrder(startNode);
        return queue.toString();
    }

    private void preOrder(Node<K, V> node){
        if(node != null) {
            stringBuilder(node);
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public String getInOrder() {
        if (root == null) {
            throw new NoSuchElementException("Tree is empty");
        }
        queue = new StringBuilder();
        Node<K, V> startNode = root;
        inOrder(startNode);
        return queue.toString();
    }

    private void inOrder(Node<K, V> node){
        if(node != null) {
            inOrder(node.getLeft());
            stringBuilder(node);
            inOrder(node.getRight());
        }
    }


    public String getPostOrder(){
        if(root == null){
            throw new NoSuchElementException("Tree is empty");
        }
        queue = new StringBuilder();
        Node<K, V> startNode = root;
        postOrder(startNode);
        return queue.toString();
    }

    private void postOrder(Node<K, V> node){
        if(node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            stringBuilder(node);
        }
    }
}
