package pl.edu.pw.ee;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HuffmanTest {

    Huffman huffman = new Huffman();
    
    @Test
    public void huffmannTest(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/folder";
        //when
        huffman.huffman(path, false);
        //then
        assert true;
    }
    @Test
    public void correctCoding(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/testingFile";
        //when
        huffman.huffman(path, true);
        //then
        String pathOrgCoded = path + "/OrgCoded.txt";
        String pathOrg = path + "/Org.txt";
        String pathOrgTree = path + "/Tree.txt";
        long sizeOrgCoded;
        long sizeOrg;
        long sizeOrgTree;

        try {
            sizeOrgCoded = Files.size(Path.of(pathOrgCoded));
            sizeOrg = Files.size(Path.of(pathOrg));
            sizeOrgTree = Files.size(Path.of(pathOrgTree));
        } catch (IOException e) {
            throw new IllegalArgumentException("Path to file is wrong!");
        }
        if(sizeOrgCoded + sizeOrgTree > sizeOrg){
            assert false;
        }
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void codingFileThatNotExistsInDirectory (){
        //given
        String path = "src/test/java/pl/edu/pw/ee/testingFile/testingFile";
        //when
        huffman.huffman(path, true);
        //then
        assert false;
    }

    @Test
    public void correctDecoding(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/testingFile";
        String pathOrg = path + "/Org.txt";
        long orgBeginningSize;
        try {
            orgBeginningSize = Files.size(Path.of(pathOrg));
        } catch (IOException e) {
            throw new IllegalArgumentException("Path to file is wrong!");
        }
        //when
        huffman.huffman(path, false);
        //then
        long orgEndingSize;
        try {
            orgEndingSize = Files.size(Path.of(pathOrg));
        } catch (IOException e) {
            throw new IllegalArgumentException("Path to file is wrong!");
        }
        if(orgEndingSize != orgBeginningSize){
            assert false;
        }
        assert true;
    }

    @Test
    public void correctCodingFileWithOneCharType(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/testingFile/testingFile2";
        //when
        huffman.huffman(path, true);
        //then
        assert true;
    }

    @Test
    public void correctDecodingFileWithOneCharType(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/testingFile/testingFile2";
        String pathOrg = path + "/Org.txt";
        long orgBeginningSize;
        try {
            orgBeginningSize = Files.size(Path.of(pathOrg));
        } catch (IOException e) {
            throw new IllegalArgumentException("Path to file is wrong!");
        }
        //when
        huffman.huffman(path, false);
        //then
        long orgEndingSize;
        try {
            orgEndingSize = Files.size(Path.of(pathOrg));
        } catch (IOException e) {
            throw new IllegalArgumentException("Path to file is wrong!");
        }
        if(orgEndingSize != orgBeginningSize){
            assert false;
        }
        assert true;
    }

}