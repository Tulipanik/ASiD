package pl.edu.pw.ee;

public class Node {

    private Character key = null;

    private Integer frequency;
    private Node left, right = null;

    public Node(Character key, Integer frequency) {
        this.key = key;
        this.frequency = frequency;
    }

    public Node(Integer frequency, Node left, Node right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public Character getKey() {
        return key;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
