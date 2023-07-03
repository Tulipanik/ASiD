package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

import java.util.Arrays;

public class RefAlgorithm implements Sorting {

    @Override
    public void sort(double[] nums) {

        if (nums == null) {
            throw new IllegalArgumentException
                    ("Array cannot be null!");
        }

        Arrays.sort(nums);
    }
}
