package pl.edu.pw.ee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class Huffman {

    WritingAndReadingElements writingMethods = new WritingAndReadingElements();

    public int huffman(String pathToRootDir, boolean compress) {
        try {
            if (compress) {
                return huffmannCoding(pathToRootDir);
            }
            return huffmannDecoding(pathToRootDir);
        } catch (IOException e) {
            throw new RuntimeException("Error with file operation!");
        }

    }

    private int huffmannCoding(String pathToRootDir) throws IOException {
        String fileToCompressPath = pathToRootDir + writingMethods.FILE_NAME_CODE;
        HashMap<Character, Integer> countedCharacters = new HashMap<>();
        File fileToCompress;
        fileToCompress = new File(fileToCompressPath);

        if (fileToCompress.length() == 0) {
            throw new IllegalArgumentException("File is empty!");

        }

        char[] character = Files.readString(fileToCompress.toPath()).toCharArray();

        for (int i = 0; i < character.length; i++) {
            if (!countedCharacters.containsKey(character[i])) {
                countedCharacters.put(character[i], 1);

            } else {
                Integer oldValue = countedCharacters.get(character[i]);
                countedCharacters.replace(character[i], oldValue, oldValue + 1);

            }
        }


        HuffmannTree tree = new HuffmannTree();
        Node root = tree.addNodes(countedCharacters);
        HashMap<Character, Boolean[]> codes = treeToCodesRecursive(root);
        String fileToWritePath = pathToRootDir + writingMethods.FILE_NAME_DECODE;
        FileOutputStream writer = new FileOutputStream(fileToWritePath);
        ArrayList<Boolean> fileList = new ArrayList<>();
        int counter = 0;

        char[] file = Files.readString(fileToCompress.toPath()).toCharArray();

        for (char nextChar : file) {
            Boolean[] nextValue = codes.get(nextChar);
            counter += nextValue.length;
            fileList.addAll(Arrays.asList(nextValue));

        }

        writingMethods.savingBoolenArrayToFile(fileList, writer, true);
        writer.close();
        writingMethods.writeTree(pathToRootDir, root);

        return counter;
    }

    private int huffmannDecoding(String pathToRootDir) throws IOException {
        HashMap<String, Character> codes = reverseHashMap
        (treeToCodesRecursive(writingMethods.readTree(pathToRootDir)));

        String pathRead = pathToRootDir + writingMethods.FILE_NAME_DECODE;
        String pathSave = pathToRootDir + writingMethods.FILE_NAME_CODE;
        File toRead = new File(pathRead);
        FileInputStream reader = new FileInputStream(toRead);
        FileWriter writer = new FileWriter(pathSave, StandardCharsets.UTF_8);

        ArrayList<Boolean> charCode = new ArrayList<>();
        byte[] file = reader.readAllBytes();

        for (byte nextChar : file) {
            Integer oneByte = (int) nextChar;
            int type = 8;
            char[] oneByteChar = writingMethods.binaryCodeToByte(oneByte, type);
            ArrayList<Boolean> oneByteCharBoolean = new ArrayList<>();
            char oneChar = '1';

            for (char bit : oneByteChar) {
                if (bit == oneChar) {
                    oneByteCharBoolean.add(true);
                    continue;

                }
                oneByteCharBoolean.add(false);

            }
            charCode.addAll(oneByteCharBoolean);

        }

        charCode = endToCut(charCode);
        String arrayCode = "";
        int counter = 0;

        while (charCode.size() > 0) {
            if (charCode.remove(0)) {
                arrayCode += '1';

            } else {
                arrayCode += '0';

            }

            if (codes.get(arrayCode) != null) {
                writer.write(codes.get(arrayCode));
                counter += 1;
                arrayCode = "";

            }
        }

        writer.close();

        return counter;

    }

    private HashMap<Character, Boolean[]> treeToCodesRecursive(Node root) {
        HashMap<Character, Boolean[]> returning = new HashMap<>();

        if(root.getRight() == null && root.getLeft() == null){
            Boolean[] code = {true};
            returning.put(root.getKey(), code);
            return returning;

        }
        ArrayList<Boolean> code = new ArrayList<>();
        branchCodes(returning, code, root);

        return returning;
    }

    private void branchCodes(HashMap<Character, Boolean[]> returning,
            ArrayList<Boolean> code, Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            returning.put(node.getKey(), code.toArray(new Boolean[0]));

        } else {
            code.add(true);
            branchCodes(returning, code, node.getLeft());
            code.remove(code.size() - 1);
            code.add(false);
            branchCodes(returning, code, node.getRight());
            code.remove(code.size() - 1);

        }
    }

    private HashMap<String, Character> reverseHashMap(HashMap<Character,
            Boolean[]> hashMap) {
        HashMap<String, Character> reversed = new HashMap<>();

        for (Character x : hashMap.keySet()) {
            String key = "";

            for (Boolean y : hashMap.get(x)) {
                if (y) {
                    key += '1';
                    continue;

                }
                key += '0';

            }
            reversed.put(key, x);

        }

        return reversed;
    }

    private ArrayList<Boolean> endToCut(ArrayList<Boolean> oneByte) {
        for (int i = oneByte.size() - 1; i >= oneByte.size() - 9; i--) {
            if (oneByte.get(i)) {
                oneByte.remove(oneByte.size() - 1);
                break;

            }
            oneByte.remove(oneByte.size() - 1);

        }

        return oneByte;
    }
}