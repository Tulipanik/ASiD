package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSortTest {

    Sorting insertionSort = new InsertionSort();
    double precision = 0.001;

    @Test(expected = IllegalArgumentException.class)
    public void arrayIsNull() {
        //given
        double[] nums = null;
        //when
        insertionSort.sort(nums);
        //then
        assert false;
    }

    @Test
    public void arrayLengthIsZero() {
        //given
        double[] nums = {};
        //when
        insertionSort.sort(nums);
        //then
        int expectedSize = 0;
        Assert.assertEquals(expectedSize, nums.length);
    }

    @Test
    public void arrayLengthIsOne() {
        //given
        double[] nums = {1};
        //when
        insertionSort.sort(nums);
        //then
        double[] expected = {1};
        Assert.assertArrayEquals(expected, nums, precision);
    }

    @Test
    public void arrayIsSortedOnStart() {
        //given
        double[] nums = {1, 2, 3, 4, 5};
        //when
        insertionSort.sort(nums);
        //then
        double[] expected = {1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expected, nums, precision);
    }

    @Test
    public void arrayIsSortedAscending() {
        //given
        double[] nums = {5, 4, 3, 2, 1};
        //when
        insertionSort.sort(nums);
        //then
        double[] expected = {1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expected, nums, precision);
    }
}
