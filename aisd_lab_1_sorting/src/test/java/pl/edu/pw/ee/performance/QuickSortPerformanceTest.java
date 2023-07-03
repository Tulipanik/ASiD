package pl.edu.pw.ee.performance;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.ee.services.Sorting;
import pl.edu.pw.ee.QuickSort;

public class QuickSortPerformanceTest {

    PerformanceTest settings = new PerformanceTest();
    Sorting quickSort = new QuickSort();
    static File file = new File
            (PerformanceTest.pathName + "QuickSortTestMeasurement.txt");


    private void swapper(int first, int second, double[] nums) {

        double tmp = nums[first];
        nums[first] = nums[second];
        nums[second] = tmp;
    }

    private void optimisticDataGenerator(double[] nums) {

        List<Integer> stack = new ArrayList<>();

        stack.add(0);
        stack.add(nums.length - 1);

        while (!stack.isEmpty()) {
            int endId = stack.remove(stack.size() - 1);
            int startId = stack.remove(stack.size() - 1);
            int middle = startId + (endId - startId) / 2;

            swapper(startId, middle, nums);

            if (middle - startId > 2) {
                stack.add(startId);
                stack.add(middle);
            }

            middle += 1;

            if (endId - middle > 2) {
                stack.add(middle);
                stack.add(endId);
            }
        }
    }

    @BeforeClass
    public static void saver() {
        PerformanceTest.setSaver(file);
    }

    @AfterClass
    public static void close() {
        PerformanceTest.save.close();
    }

    @Test
    public void optimisticData() {

        PerformanceTest.save.print("OptimisticData; ");

        for (int i = 2; i <= settings.howMany; i += settings.length) {
            settings.dataGenerator(i, 0);

            if (settings.nums.length > 2) {
                optimisticDataGenerator(settings.nums);
            }

            settings.measurement(quickSort);
        }

        PerformanceTest.save.println("\n");
        assert true;
    }

    @Test
    public void randomData() {

        PerformanceTest.save.print("RandomData; ");

        for (int i = 2; i <= settings.howMany; i += settings.length) {
            settings.dataGenerator(i, 2);
            settings.measurement(quickSort);
        }

        PerformanceTest.save.println("\n");
        assert true;
    }

    @Test
    public void pessimisticData() {

        PerformanceTest.save.print("PessimisticData; ");

        for (int i = 2; i <= settings.howMany; i += settings.length) {
            settings.dataGenerator(i, 1);
            settings.measurement(quickSort);
        }

        PerformanceTest.save.println("\n");
        assert true;
    }

}
