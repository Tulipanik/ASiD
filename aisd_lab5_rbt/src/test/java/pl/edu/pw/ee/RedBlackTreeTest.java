package pl.edu.pw.ee;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RedBlackTreeTest {

    final static double DELTA = 0.005;
    
    @Test
    public void buildingATree(){
        //given
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
       //when
        for(int i = 5; i>1; i--){
            tree.put(i, i);
        }
        //then
        assert true;
    }

    @Test(expected = NoSuchElementException.class)
    public void gettingPreOrderWithNullValue(){
        //given
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
        //when
        tree.getPreOrder();
        //then
        assert false;
    }

    @Test
    public void gettingPreOrderInTree(){
        //given
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        String[] array = {"H", "D", "B", "F", "L", "J", "N"};
        for(String i: array){
            tree.put(i, i);
        }
        //when
        String returned = tree.getPreOrder();
        //then
        String expected = "H:H D:D B:B F:F L:L J:J N:N ";
        assertEquals(expected, returned);
    }
    
    @Test
    public void gettingInOrderInTree(){
        //given
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        String[] array = {"H", "D", "B", "F", "L", "J", "N"};
        for(String i: array){
            tree.put(i, i);
        }
        //when
        String returned = tree.getInOrder();
        //then
        String expected = "B:B D:D F:F H:H J:J L:L N:N ";
        assertEquals(expected, returned);
    }
    
    @Test
    public void gettingPostOrderInTree(){
        //given
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        String[] array = {"H", "D", "B", "F", "L", "J", "N"};
        for(String i: array){
            tree.put(i, i);
        }
        //when
        String returned = tree.getPostOrder();
        //then
        String expected = "B:B F:F D:D J:J N:N L:L H:H ";
        assertEquals(expected, returned);
    }

    @Test(expected = NoSuchElementException.class)
    public void gettingInOrderWithNullValue(){
        //given
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
        //when
        tree.getInOrder();
        //then
        assert false;
    }

    @Test(expected = NoSuchElementException.class)
    public void gettingPostOrderWithNullValue(){
        //given
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
        //when
        tree.getPostOrder();
        //then
        assert false;
    }
 
    
    @Test
    public void deleteMaximalElement(){
        //given
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
        int howManyElements = 10;
        
        for(int i = howManyElements; i>1; i--){
            tree.put(i, i);
        }
        //when
        tree.deleteMax();
        String received = tree.getInOrder();
        //then
        String [] array = received.split(" ");
        String expected1 = "9:9";
        assertEquals(expected1, array[array.length-1]);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void puttingWithNullKey(){
        //given
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
        Integer keyAndValue = null;
        //when
        tree.put(keyAndValue, keyAndValue);
    }
}
    
