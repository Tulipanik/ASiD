package pl.edu.pw.ee;

import org.junit.Test;

public class RbtMapTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void addingNodeWithNullKeyAndValue(){
        //given
        RbtMap<Integer, Integer> tree = new RbtMap<>();
        Integer key = null;
        Integer value = null;
        //when
        tree.setValue(key, value);
        //then
        assert false;
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void gettingNodeWithNullKeyAndValue(){
        //given
        RbtMap<Integer, Integer> tree = new RbtMap<>();
        Integer key = null;
        //when
        tree.getValue(key);
        //then
        assert false;
    }
    
    @Test
    public void settingSomeValues(){
        //given
        RbtMap<Integer, Integer> tree = new RbtMap<>();
        //when
        tree.setValue(25, 2);
        tree.setValue(31, 3);
        tree.setValue(12, 1);
        tree.setValue(41, 4);
        tree.setValue(37,37);
        tree.setValue(28,28);
        tree.setValue(18,18);
        //then
        assert true;
    }
    
    @Test
    public void gettingCorrectSomeValues(){
        //given
        RbtMap<Integer, Integer> tree = new RbtMap<>();
        
        tree.setValue(25, 2);
        tree.setValue(31, 3);
        tree.setValue(12, 1);
        tree.setValue(41, 4);
        tree.setValue(37,37);
        tree.setValue(28,28);
        tree.setValue(18,18);
        //when
        tree.getValue(41);
        tree.getValue(37);
        //then
        assert true;
    }

}
