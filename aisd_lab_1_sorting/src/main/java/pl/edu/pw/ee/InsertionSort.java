package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {

        if (nums == null) {
            throw new IllegalArgumentException
                    ("Array cannot be null!");
        }

        int length = nums.length;
        double current;

        for (int i = 1; i < length; i++) {
            current = nums[i];

            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] > current) {
                    nums[j + 1] = nums[j];
                    nums[j] = current;

                } else {
                    break;
                }
            }
        }
    }
}
