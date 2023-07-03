package pl.edu.pw.ee.performance;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import pl.edu.pw.ee.services.Sorting;
import pl.edu.pw.ee.SelectionSort;

public class SelectionSortPerformanceTest {

    Sorting selectionSort = new SelectionSort();
    PerformanceTest settings = new PerformanceTest();
    static File file = new File(PerformanceTest.pathName + "SelectionSortTestMeasurement.txt");

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
            settings.measurement(selectionSort);
        }

        PerformanceTest.save.println("\n");
        assert true;
    }

    @Test
    public void randomData() {

        PerformanceTest.save.print("RandomData; ");

        for (int i = 2; i <= settings.howMany; i += settings.length) {
            settings.dataGenerator(i, 2);
            settings.measurement(selectionSort);
        }

        PerformanceTest.save.println("\n");
        assert true;
    }

    @Test
    public void pessimisticData() {

        PerformanceTest.save.print("PessimisticData; ");

        for (int i = 0; i < settings.howMany; i += settings.length) {
            settings.dataGenerator(i, 1);
            settings.measurement(selectionSort);
        }

        PerformanceTest.save.println("\n");
        assert true;
    }
}
