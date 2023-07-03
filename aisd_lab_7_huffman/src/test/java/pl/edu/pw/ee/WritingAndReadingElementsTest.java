package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.WritingAndReadingElements;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WritingAndReadingElementsTest {

    WritingAndReadingElements elements = new WritingAndReadingElements();

    @Test(expected = IllegalArgumentException.class)
    public void savingNullBooleanArray(){
        //given
        ArrayList <Boolean> array = new ArrayList<>();
        FileOutputStream writer = null;
        //when
        elements.savingBoolenArrayToFile(array, writer, true);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertingBytesToBooleanTypeTypeIs9AndRestArgumentsAreNull(){
        //given
        ArrayList<Boolean> toSave = null;
        Integer oneByte = null;
        int type = 9;
        //when
        elements.convertingBytesToBooleanType(toSave, oneByte, type);
        //then
        assert false;
    }

    @Test
    public void convertingBytesToBooleanTypeWithType16(){
        //given
        ArrayList<Boolean> toSave = new ArrayList<>();
        Integer oneByte =17;
        int type = 16;
        //when
        elements.convertingBytesToBooleanType(toSave, oneByte, type);
        //then
        if(toSave.size() != type){
            assert false;
        }
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void writingTreeWithNullRoot(){
        //given
        Node root = null;
        String pathToDir = "";
        //when
        try {
            elements.writeTree(pathToDir, root);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read the file!");
        }
        //then
        assert false;

    }
}
