package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class HeapSortTest {

    Sorting heapSort = new HeapSort();
    double precision = 0.001;

    @Test(expected = IllegalArgumentException.class)
    public void dataIsNull() {
        //given
        double[] data = null;
        //when
        heapSort.sort(data);
        //then
        assert false;
    }

    @Test
    public void dataArrayHasLengthZero() {
        //given
        double[] data = {};
        //when
        heapSort.sort(data);
        //then
        int expected = 0;
        Assert.assertEquals(expected, data.length);
    }

    @Test
    public void dataArrayLengthIsOne() {
        //given
        double[] data = {1};
        //when
        heapSort.sort(data);
        //then
        double[] expected = {1};
        Assert.assertArrayEquals(expected, data, precision);
    }

    @Test
    public void dataArrayIsAlreadySorted() {
        //given
        double[] data = {1, 2, 3, 4, 5};
        //when
        heapSort.sort(data);
        //then
        double[] expected = {1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expected, data, precision);
    }

    @Test
    public void dataArrayIsSortedAscending() {
        //given
        double[] data = {5, 4, 3, 2, 1};
        //when
        heapSort.sort(data);
        //then
        double[] expected = {1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expected, data, precision);
    }
}
