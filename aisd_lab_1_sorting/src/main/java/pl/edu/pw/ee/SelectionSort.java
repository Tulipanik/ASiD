package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {

        if (nums == null) {
            throw new IllegalArgumentException
                    ("Array given to method cannot be null!");
        }

        int n = nums.length;
        int maxValid;

        for (int i = 0; i < n; i++) {
            maxValid = i;

            for (int j = i; j < n; j++) {
                if (nums[j] < nums[maxValid]) {
                    maxValid = j;
                }
            }

            swap(nums, maxValid, i);
        }
    }

    private void swap(double[] nums, int firstId, int secondId) {
        if (firstId != secondId) {
            double firstVal = nums[firstId];
            nums[firstId] = nums[secondId];
            nums[secondId] = firstVal;
        }
    }
}
