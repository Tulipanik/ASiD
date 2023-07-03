package pl.edu.pw.ee;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimAlgorithmTest {

    PrimAlgorithm prim = new PrimAlgorithm();

    @Test
    public void correctTest(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/files/CorrectSmallData.txt";
        //when
        String actual = prim.findMST(path);
        //then
        String expected = "A_3_B|B_1_C|C_1_D|D_7_E";
        assertEquals(expected, actual);
    }

    @Test
    public void correctTestKruskal(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/files/CorrectSmallData.txt";
        //when
        String actual = prim.kruskal(path);
        //then
        String expected = "A_3_B|B_1_C|C_1_D|D_7_E";
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneOfWeightsHasDoubleFormat(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/files/WeightIsInDoubleFormat.txt";
        //when
        prim.findMST(path);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneOfNodesIsNotCorrectChar(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/files/NodeIsNotCorrectChar.txt";
        //when
        prim.findMST(path);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void lineHasIncorrectParameters(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/files/IncorrectParametersInLine.txt";
        //when
        prim.findMST(path);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneOfWeightsIsLowerThanZero(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/files/WeightIsLoverThanZero.txt";
        //when
        prim.findMST(path);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void fileNotExistWithGivenPath(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/files/FileNotExistInHere.txt";
        //when
        prim.findMST(path);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void graphFromFileIsNotCoherent(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/files/GraphIsNotCoherent.txt";
        //when
        prim.findMST(path);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void thereIsALoopInGraph(){
        //given
        String path = "src/test/java/pl/edu/pw/ee/files/ThereIsALoopInGraph.txt";
        //when
        prim.findMST(path);
        //then
        assert false;
    }
}
