package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SurpriseTest {

    Surprise surprise = new Surprise();

    @Test
    public void correctArrayWithDifferentValues() {
        //given
        int[] randomQueueWith3Solution = {1, 2, 3, 4, 5};
        //when
        int result = surprise.findNumOf(randomQueueWith3Solution);
        //then
        int expectedResult = 10;
        assertEquals(expectedResult, result);
    }

    @Test
    public void correctArrayWithSameValues() {
        //given
        int[] randomQueueWithSameSolution = {1, 1, 1, 1, 1};
        //when
        int result = surprise.findNumOf(randomQueueWithSameSolution);
        //then
        int expectedResult = 1;
        assertEquals(expectedResult, result);
    }

    @Test
    public void queueIsShorterthan3() {
        //given
        int[] randomQueueWith2Solution = {1, 1};
        //when
        int result = surprise.findNumOf(randomQueueWith2Solution);
        //then
        int expectedResult = 0;
        assertEquals(expectedResult, result);

    }

    @Test
    public void queueHasValueBiggerThanOneMiliard() {
        //given
        final int THE_BIGGEST = 10000;
        int[] randomQueueWithBigSolution = new int[THE_BIGGEST];
        for (int i = 0; i < THE_BIGGEST; i++) {
            randomQueueWithBigSolution[i] = i;
        }
        //when
        int result = surprise.findNumOf(randomQueueWithBigSolution);
        //then
        if (result < THE_BIGGEST) {
            assert true;
        }
        assert false;

    }
}
