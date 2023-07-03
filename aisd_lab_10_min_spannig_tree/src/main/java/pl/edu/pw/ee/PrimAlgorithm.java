package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {
    ArrayList<String> nodeTable = new ArrayList<>();
    ArrayList<Connection> connections = new ArrayList<>();

    public String findMST(String pathToFile) {
        Integer[][] matrix = matrixOfGraph(pathToFile);

        PriorityQueue<Connection> connections = new PriorityQueue<>();
        for (int i = 0; i < nodeTable.size(); i++) {
            if (matrix[0][i] != null) {
                Connection connectionToAdd = new Connection(nodeTable.get(0), nodeTable.get(i), matrix[0][i]);
                connections.add(connectionToAdd);
            }
        }

        ArrayList<String> visited = new ArrayList<>();
        visited.add(nodeTable.get(0));
        StringBuilder tree = new StringBuilder();

        while (!connections.isEmpty()) {
            Connection connection = connections.poll();
            if (visited.contains(connection.getTo())) {
                continue;
            }
            tree.append("|").append(connection.getFrom()).append("_")
                    .append(connection.getWeight()).append("_").append(connection.getTo());
            visited.add(connection.getTo());
            int indexOfTo = nodeTable.indexOf(connection.getTo());
            for (int i = 0; i < nodeTable.size(); i++) {
                if (matrix[indexOfTo][i] != null) {
                    Connection connectionToAdd = new Connection(connection.getTo(),
                            nodeTable.get(i), matrix[indexOfTo][i]);
                    connections.add(connectionToAdd);
                }
            }
        }
        if (visited.size() < nodeTable.size()) {
            throw new IllegalArgumentException("Graph is not coherent!");
        }
        tree.replace(0, 1, "");

        return tree.toString();
    }

    private Integer[][] matrixOfGraph(String pathToFile) {

        readGraph(pathToFile);
        int size = nodeTable.size();
        Integer[][] matrix = new Integer[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Connection connection = hasConnection(nodeTable.get(i), nodeTable.get(j));
                if (connection != null) {
                    matrix[i][j] = connection.getWeight();
                } else {
                    matrix[i][j] = null;
                }
            }
        }

        connections.clear();
        return matrix;
    }

    private Connection hasConnection(String from, String to) {
        for (Connection x : connections) {
            if (x.getFrom().equals(from) && x.getTo().equals(to)) {
                return x;
            }
        }
        return null;
    }

    private void readGraph(String pathToFile) {
        Scanner reader;
        try {
            reader = new Scanner(new File(pathToFile));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Path to file is incorrect!");
        }

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] lineArray = line.split(" ");

            if (lineArray.length != 3) {
                throw new IllegalArgumentException("Building of File is wrong!");
            }

            if (lineArray[0].equals(lineArray[1])) {
                throw new IllegalArgumentException("Graph cannot have loops!");
            }


            char[] from = lineArray[0].toCharArray();
            char[] to = lineArray[1].toCharArray();

            checkCode(from);
            checkCode(to);
            int x;

            try {
                x = Integer.parseInt(lineArray[2]);
            } catch (Exception e) {
                throw new IllegalArgumentException("Weight have to be integer value!");
            }

            if (x < 0) {
                throw new IllegalArgumentException("Weight should be 0 or grater!");
            }

            connections.add(new Connection(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2])));
            if (!nodeTable.contains(lineArray[0])) {
                nodeTable.add(lineArray[0]);
            }
            if (!nodeTable.contains(lineArray[1])) {
                nodeTable.add(lineArray[1]);
            }
        }
    }

    private void checkCode(char[] stringToCheck) {
        for (char c : stringToCheck) {
            int fromCode = (int) c;
            if ((fromCode < 65 || fromCode > 90) && (fromCode < 97 || fromCode > 122)) {
                throw new IllegalArgumentException("Node should have characters A-Z or a-z!");
            }
        }
    }

    public String kruskal(String pathToFile) {
        readGraph(pathToFile);

        PriorityQueue<Connection> priorityQueue = new PriorityQueue<>();

        priorityQueue.addAll(connections);

        ArrayList<ArrayList<Connection>> paths = new ArrayList<>();

        while(!priorityQueue.isEmpty()){
            Connection connection = priorityQueue.poll();
            checkIfNotCycle(paths, connection);
        }

        StringBuilder tree = new StringBuilder();
        for (int i = 0; i < paths.size(); i++){
            for(int j = 0; j<paths.get(i).size(); j++){
                Connection connection = paths.get(i).get(j);
                tree.append("|").append(connection.getFrom()).append("_")
                        .append(connection.getWeight()).append("_").append(connection.getTo());
            }
        }

        tree.replace(0, 1, "");

        return tree.toString();

    }

    private void checkIfNotCycle(ArrayList<ArrayList<Connection>> paths, Connection connection){
        boolean isFrom = false;
        boolean isTo = false;
        int[] position = {-1,-1};
        for(int i = 0; i < paths.size(); i++){
            for(int j = 0; j < paths.get(i).size(); j++){
                Connection connection1 = paths.get(i).get(j);
                if(connection1.getFrom().equals(connection.getFrom())){
                    isFrom = true;
                    position[0] = i;
                }
                if(connection1.getTo().equals(connection.getTo())){
                    isTo = true;
                    position[1] = i;
                }
            }
        }
        if(isFrom && isTo && position[0] == position[1]){
            return;
        }
        if(isFrom && isTo && position[0] != position[1]){
            ArrayList<Connection> toPut= arrayConcat(paths.get(position[0]), paths.get(position[1]));
            paths.remove(position[0]);
            paths.remove(position[1]);
            paths.add(toPut);
            return;
        }
        if(!isFrom && isTo){
            paths.get(position[1]).add(connection);
            return;
        }
        if(isFrom){
            paths.get(position[0]).add(connection);
        }
        ArrayList<Connection> list = new ArrayList<>();
        list.add(connection);
        paths.add(list);
    }

    private ArrayList<Connection> arrayConcat(ArrayList<Connection> first,  ArrayList<Connection> second){
        ArrayList<Connection> toReturn = new ArrayList<>();
        toReturn.addAll(first);
        toReturn.addAll(second);
        return toReturn;
    }
}