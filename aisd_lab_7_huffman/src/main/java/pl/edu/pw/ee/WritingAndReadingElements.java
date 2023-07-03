package pl.edu.pw.ee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

public class WritingAndReadingElements {

    final String FILE_NAME_CODE = "\\Org.txt";
    final String FILE_NAME_DECODE = "\\OrgCoded.txt";
    final String FILE_NAME_TREE = "\\Tree.txt";

    public void savingBoolenArrayToFile(ArrayList<Boolean> fileList,
            FileOutputStream writer, boolean isSavingFile) {
        BitSet saveUnit;

        if (fileList == null || writer == null) {
            throw new IllegalArgumentException("file List cannot be null!");
        }

        if (isSavingFile) {
            saveUnit = new BitSet((int) fileList.size() + 1);
            saveUnit.set(fileList.size() + 1);

        } else {
            saveUnit = new BitSet((int) fileList.size());

        }

        for (int i = 0; i < fileList.size(); i++) {
            if (fileList.get(i)) {
                saveUnit.set(i);

            }
        }

        try {
            writer.write(saveUnit.toByteArray());
        } catch (IOException e) {
            throw new IllegalArgumentException("Exception");
        }
    }

    public void writeTree(String pathToRootDir, Node root) throws IOException {

        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }

        ArrayList<Boolean> booleanTree = new ArrayList<>();
        writeTreeRecursive(root, booleanTree);

        String path = pathToRootDir + FILE_NAME_TREE;
        File tree = new File(path);
        FileOutputStream writer = new FileOutputStream(tree);
        savingBoolenArrayToFile(booleanTree, writer, false);
        writer.close();
    }

    private void writeTreeRecursive(Node node, ArrayList<Boolean> booleanTree) {

        if (node.getRight() == null && node.getLeft() == null) {
            booleanTree.add(true);

            Character leaf = node.getKey();
            Boolean[] characterSet = new Boolean[16];
            char[] binary = Integer.toBinaryString(leaf).toCharArray();
            char oneChar = '1';
            int j = binary.length;

            Arrays.fill(characterSet, false);

            for (int i = characterSet.length - 1;
                    i >= characterSet.length - binary.length; i--) {
                j -= 1;
                if (binary[j] == oneChar) {
                    characterSet[i] = true;
                    continue;

                }
                characterSet[i] = false;

            }

            booleanTree.addAll(Arrays.asList(characterSet));
            return;

        }
        booleanTree.add(false);
        writeTreeRecursive(node.getLeft(), booleanTree);
        writeTreeRecursive(node.getRight(), booleanTree);
    }

    public ArrayList<Boolean> convertingBytesToBooleanType
        (ArrayList<Boolean> bytes, Integer oneByte, int type) {
        if (bytes == null || oneByte == null || (type != 16 && type != 8)) {
            throw new IllegalArgumentException
        ("Elements cannot be null and type should be 16 or 8");
        }

        char[] byteList;

        if (type != 16) {
            byteList = binaryCodeToByte(oneByte, type);

        } else {
            byteList = binaryCodeToByteTurn(oneByte, type);

        }
        for (int i = 0; i < byteList.length; i++) {
            if (byteList[i] == '1') {
                bytes.add(true);
                continue;

            }
            bytes.add(false);

        }
        return bytes;

    }

    public Node readTree(String pathToRootDir) throws IOException {
        String path = pathToRootDir + FILE_NAME_TREE;
        FileInputStream reader = new FileInputStream(path);
        ArrayList<Boolean> bytes = new ArrayList<>();
        int type = 8;
        Integer oneByte;

        while ((oneByte = reader.read()) != -1) {
            bytes = convertingBytesToBooleanType(bytes, oneByte, type);

        }
        ArrayList<Node> stack = new ArrayList<>();

        if (!bytes.get(0)) {
            stack.add(new Node(0, null, null));
            bytes.remove(0);

        }else{
            bytes.remove(0);
            return new Node(makeBinary(bytes), 0);
        }

        Node root = stack.get(0);

        while (!bytes.isEmpty()) {
            Boolean x = bytes.remove(0);

            if (!x) {
                Node bufor = new Node(0, null, null);

                while (!stack.isEmpty()) {
                    Node topStack = stack.get(stack.size() - 1);

                    if (topStack.getLeft() == null) {
                        topStack.setLeft(bufor);
                        stack.add(topStack.getLeft());
                        break;

                    } else if (topStack.getLeft() != null &&
                            topStack.getRight() == null) {
                        topStack.setRight(bufor);
                        stack.add(topStack.getRight());
                        break;

                    } else {
                        stack.remove(stack.size() - 1);

                    }
                }
            } else {
                char code = makeBinary(bytes);

                while (!stack.isEmpty()) {
                    Node topStack = stack.get(stack.size() - 1);

                    if (topStack.getLeft() == null) {
                        topStack.setLeft(new Node(code, 0));
                        break;

                    } else if (topStack.getLeft() != null &&
                            topStack.getRight() == null) {
                        topStack.setRight(new Node(code, 0));
                        break;

                    } else {
                        stack.remove(stack.size() - 1);

                    }
                }
            }
        }

        return root;
    }


    public char[] binaryCodeToByte(Integer oneByte, int type) {
        char[] byteList = Integer.toBinaryString(oneByte).toCharArray();
        char[] toReturn = new char[type];

        for (int i = 0; i < toReturn.length; i++) {
            if (byteList.length - 1 - i < 0) {
                toReturn[i] = '0';
                continue;

            }
            toReturn[i] = byteList[byteList.length - 1 - i];

        }

        return toReturn;
    }

    public char[] binaryCodeToByteTurn(Integer oneByte, int type) {
        char[] byteList = Integer.toBinaryString(oneByte).toCharArray();
        char[] toReturn = new char[type];

        for (int i = 0; i < toReturn.length; i++) {
            if (i - (type - byteList.length) < 0) {
                toReturn[i] = '0';
                continue;

            }
            toReturn[i] = byteList[i - (type - byteList.length)];

        }

        return toReturn;
    }

    private char makeBinary (ArrayList<Boolean> bytes){
        String character = "";

        for (int i = 0; i < 16; i++) {
            if (bytes.remove(0)) {
                character += '1';
                continue;

            }
            character += '0';

        }
        return (char) Integer.parseInt(character, 2);
    }
}