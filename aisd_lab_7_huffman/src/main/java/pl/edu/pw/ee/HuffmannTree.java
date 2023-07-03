package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.HashMap;

public class HuffmannTree {

    private ArrayList<Node> frequencyArray = new ArrayList<>();

    public Node addNodes(HashMap<Character, Integer> leaves) {
        if (leaves == null) {
            throw new IllegalArgumentException("Leaves cannot be null!");

        }

        for (Character key : leaves.keySet()) {
            Integer value = leaves.get(key);

            if (key == null || value == null) {
                throw new IllegalArgumentException("Key or value cannot be null!");

            }
            Node newNode = new Node(key, value);
            frequencyArray.add(newNode);

        }
        return bulidTree();

    }

    private Node theLowestInFrequencyArray() {
        if (frequencyArray.isEmpty()) {
            throw new IllegalArgumentException("Tree have to have at least one leave!");

        }

        Node theLowest = frequencyArray.get(0);

        for (Node node : frequencyArray) {
            if (theLowest.getFrequency() > node.getFrequency()) {
                theLowest = node;

            }
        }
        frequencyArray.remove(theLowest);

        return theLowest;
    }

    private Node bulidTree() {
        while (frequencyArray.size() != 1) {
            Node first = theLowestInFrequencyArray();
            Node second = theLowestInFrequencyArray();
            Integer frequency = first.getFrequency() + second.getFrequency();
            Node connection = new Node(frequency, second, first);
            frequencyArray.add(connection);

        }
        return frequencyArray.get(0);
    }
}
