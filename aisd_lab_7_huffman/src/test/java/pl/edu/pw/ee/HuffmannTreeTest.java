package pl.edu.pw.ee;

import org.junit.Test;

import java.util.HashMap;

public class HuffmannTreeTest {

    HuffmannTree tree = new HuffmannTree();

    @Test(expected = IllegalArgumentException.class)
    public void addingNullKeyAndValueToLeavesHashMap(){
        //given
        HashMap<Character, Integer> nodes = new HashMap<>();
        nodes.put(null, null);
        //when
        tree.addNodes(nodes);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNullLeavesHashMap(){
        //given
        HashMap<Character, Integer> nodes = null;
        //when
        tree.addNodes(nodes);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void leavesHashMapIsEmpty(){
        //given
        HashMap<Character, Integer> nodes = new HashMap<>();
        //when
        tree.addNodes(nodes);
        //then
        assert false;
    }

}
