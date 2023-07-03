package pl.edu.pw.ee.performance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import pl.edu.pw.ee.services.Sorting;

public class PerformanceTest {

    double[] nums;
    int length = 2;
    int quantity = 1000;
    int howMany = length * quantity;
    static PrintWriter save;
    static String pathName = "src/test/java/pl/edu/pw/ee/performance/output/";

    public static void setSaver(File file) {

        try {
            save = new PrintWriter(file);
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("File not exists!");
        }
    }

    public void measurement(Sorting whichSort) {

        long actualTime = System.nanoTime();
        whichSort.sort(nums);
        long time = System.nanoTime() - actualTime;

        save.print(time + "; ");
    }

    public void dataGenerator(int length, int mode) {

        nums = new double[length];

        if (mode == 0) {
            for (int i = 0; i < length; i++) {
                nums[i] = i;
            }

        } else if (mode == 1) {
            for (int i = 0; i < length; i++) {
                nums[i] = length - i;
            }

        } else if (mode == 2) {
            Random randomizer = new Random();
            for (int i = 0; i < length; i++) {
                nums[i] = randomizer.nextDouble();
            }

        } else if (mode == 3) {
            int randomConstant = 2;

            for (int i = 0; i < length; i++) {
                nums[i] = randomConstant;
            }

        } else {
            nums = null;
        }
    }
}
