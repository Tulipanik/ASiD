package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class LongestCommonSubsequence {

    private final String[] topStr;
    private final String[] leftStr;
    private FieldOfMatrix[][] matrix;
    private final int topLength;
    private final int leftLength;
    private final ArrayList<int[]> pathOfSubsequence = new ArrayList<>();
    private final HashMap<String, String> white = new HashMap<>() {{
        put("\n", "\\n");
        put("\t", "\\t");
        put("\f", "\\f");
        put("\b", "\\b");
        put("\r", "\\r");
    }};

    public LongestCommonSubsequence(String topStr, String leftStr) {
        if (topStr == null || leftStr == null) {
            throw new IllegalArgumentException("Any argument cannot be null!");
        }

        this.topStr = topStr.split("");
        this.leftStr = leftStr.split("");
        this.topLength = topStr.length() + 1;
        this.leftLength = leftStr.length() + 1;
        createMatrix();
    }

    private void createMatrix() {
        matrix = new FieldOfMatrix[leftLength][topLength];

        for (int i = 0; i < topLength; i++) {
            matrix[0][i] = new FieldOfMatrix(0, 0, i, 0);
        }

        for (int i = 0; i < leftLength; i++) {
            matrix[i][0] = new FieldOfMatrix(0, 0, 0, i);
        }

        for (int left = 1; left < leftLength; left++) {
            for (int top = 1; top < topLength; top++) {
                if (topStr[top - 1].equals(leftStr[left - 1])) {
                    matrix[left][top] = new FieldOfMatrix(1, matrix[left - 1][top - 1].getValue() + 1, top, left);
                } else {
                    int fieldValue = 0;
                    int direction = 2;
                    if (fieldValue <= matrix[left - 1][top].getValue()) {
                        fieldValue = matrix[left - 1][top].getValue();
                    }

                    if (matrix[left][top - 1].getValue() > fieldValue) {
                        fieldValue = matrix[left][top - 1].getValue();
                        direction = 3;
                    }
                    matrix[left][top] = new FieldOfMatrix(direction, fieldValue, top, left);
                }
            }
        }
    }

    public String findLCS() {

        FieldOfMatrix beginning = matrix[leftLength - 1][topLength - 1];
        pathOfSubsequence.clear();
        StringBuilder Subsequence = new StringBuilder();

        while (beginning.getValue() != 0) {
            int[] fieldParams = new int[2];
            fieldParams[0] = beginning.getIndexLeft();
            fieldParams[1] = beginning.getIndexTop();
            pathOfSubsequence.add(fieldParams);

            if (beginning.getDirection() == 1) {
                Subsequence.append(topStr[fieldParams[1] - 1]);
                beginning = matrix[fieldParams[0] - 1][fieldParams[1] - 1];

            } else if (beginning.getDirection() == 2) {
                beginning = matrix[fieldParams[0] - 1][fieldParams[1]];

            } else {
                beginning = matrix[fieldParams[0]][fieldParams[1] - 1];
            }
        }
        return Subsequence.reverse().toString();
    }

    public void display() {
        System.out.print("\n");
        printingHorizontalBarrier();
        printingBetweenNullValues();
        printWordHorizontally();
        printingBetweenNullValues();

        for (int i = 0; i < leftLength; i++) {
            printingHorizontalBarrier();
            printingBetweenValuesAndBarriers(i);
            int index = i - 1;
            printRow(index, i);
            printingBetweenNullValues();
        }
        printingHorizontalBarrier();
    }

    protected void printingHorizontalBarrier() {
        System.out.print("+-------");

        for (int i = 0; i < topLength; i++) {
            System.out.print("+-----");
        }
        System.out.println("+");
    }

    protected void printingBetweenValuesAndBarriers(int row) {
        rowVerifier(row);
        System.out.print("|       ");

        for (int i = 0; i < topLength; i++) {
            int[] position = {row, i};
            if (contains(position)) {
                printDirection(position, false);
            } else {
                System.out.print("|   ");
            }
            System.out.print("  ");
        }
        System.out.println("|");
    }

    protected void printingBetweenNullValues() {
        System.out.print("|       ");

        for (int i = 0; i < topLength; i++) {
            System.out.print("|     ");
        }
        System.out.println("|");
    }

    protected void printWordHorizontally() {
        System.out.print("|       |     ");

        for (int i = 0; i < topLength - 1; i++) {
            if (white.containsKey(topStr[i])) {
                System.out.print("| " + getFromWhites(topStr[i]));
            } else {
                System.out.print("|  " + topStr[i]);
            }
            System.out.print("  ");
        }
        System.out.println("|");
    }

    protected void printRow(int indexOfWord, int row) {
        rowVerifier(row);
        wordFieldPrint(indexOfWord);

        for (int i = 0; i < topLength; i++) {
            int[] position = {row, i};
            if (contains(position)) {
                printDirection(position, true);
            } else {
                System.out.print("|  ");
            }
            System.out.print(matrix[row][i].getValue());
            System.out.print("  ");
        }
        System.out.println("|");
    }

    protected String getFromWhites(String key) {
        return white.getOrDefault(key, null);
    }

    private void wordFieldPrint(int indexOfWord) {
        if (indexOfWord == -1) {
            System.out.print("|       ");
        } else {
            if (white.containsKey(leftStr[indexOfWord])) {
                System.out.print("|  " + getFromWhites(leftStr[indexOfWord]));
            } else {
                System.out.print("|   " + leftStr[indexOfWord]);
            }
            System.out.print("   ");
        }
    }

    protected void printDirection(int[] position, boolean side) {
        int direction;

        try {
            direction = matrix[position[0]][position[1]].getDirection();
        } catch (Exception outsideMatrix) {
            throw new IllegalArgumentException("Position not exists in matrix!");
        }

        if (side && direction == 3) {
            System.out.print("|< ");
        } else if (!side) {
            if (direction == 1) {
                System.out.print("|\\  ");
            } else if (direction == 2) {
                System.out.print("|  ^");
            } else {
                System.out.print("|   ");
            }
        } else {
            System.out.print("|  ");
        }
    }

    private void rowVerifier(int row) {
        if (row < 0 || row > leftLength - 1) {
            throw new IllegalArgumentException("Row is wrong!");
        }
    }

    private boolean contains(int[] value) {
        for (int[] x : pathOfSubsequence) {
            if (Arrays.equals(x, value)) {
                return true;
            }
        }
        return false;
    }
}