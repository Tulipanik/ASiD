package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class AccessMethods {

    int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    int getSize(HashTable<?> hash) {
        String fieldSize = "size";
        try {
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldSize);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

     String[] generateChainsWithCollisionsOnFirst(int hashSize, int howMany) {
        if(hashSize<0){
            throw new IllegalArgumentException("Size cannot be less than 0 or null!");
        }
        String first = "easter eg";
        char last = 'g';
        ArrayList<String> listOfStrings = new ArrayList<>();
        listOfStrings.add(first + last);

        for (int i = hashSize; i < howMany * hashSize; i += hashSize) {
            last += i;
            listOfStrings.add(first + last);
        }
        return listOfStrings.toArray(new String[0]);
    }
}
