package pl.edu.pw.ee;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class HashListChainingPerformanceTest {

    static final int BEGIN = 4096;
    static final int MEASUREMENT_COUNT = 30;
    static final int WORDS_COUNT = 100000;
    static final int AVERAGE_END = 20;
    static final int AVERAGE_START = 10;

    HashListChaining<String> hash;
    ArrayList<Long> times = new ArrayList<Long>();
    static String[] words = new String[WORDS_COUNT];

    static final String path = "src/test/java/pl/edu/pw/ee/inout/";
    static final File wordsFile = new File(path + "words.txt");
    static final File file = new File(path + "HashListChainingPerformanceTest.txt");

    static Scanner reader;
    static PrintWriter writer;

    private static void readerAndWriterSetter() {

        try {
            reader = new Scanner(wordsFile);
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    private void measurement() {

        long start = System.nanoTime();

        for (int i = 0; i < words.length; i++) {
            hash.get(words[i]);
        }

        long end = System.nanoTime();
        times.add(end - start);
    }

    private long average() {

        long sum = 0;
        Collections.sort(times);

        for (int i = AVERAGE_START; i < AVERAGE_END; i++) {
            sum += times.get(i);
        }

        return sum / AVERAGE_START;
    }

    private void setHashList(int size) {

        hash = new HashListChaining<>(size);

        for (String i : words) {
            hash.add(i);
        }
    }

    @BeforeClass
    public static void wordReader() {

        readerAndWriterSetter();

        for (int i = 0; i < WORDS_COUNT; i++) {
            if (reader.hasNext()) {
                words[i] = reader.nextLine();

            } else {
                throw new IllegalArgumentException
                        ("Data file has not enough words");
            }
        }

        reader.reset();
    }

    @AfterClass
    public static void closer() {

        writer.close();
    }

    @Test
    public void performanceWithPrimeValue() {

        writer.println("Prime numbers\n");
        int[] primeNumbers = {4093, 8191, 16381, 32771, 65537, 131071, 262147};

        for (int i = 0; i < primeNumbers.length; i++) {
            setHashList(primeNumbers[i]);

            for (int j = 0; j < MEASUREMENT_COUNT; j++) {
                measurement();
            }

            writer.println(average() + "\n");
            times.clear();
        }
    }

    @Test
    public void performanceWithBeginValue() {

        writer.println("Multiples of " + BEGIN + "\n");
        final int maxMultiplier = 64;

        for (int i = 1; i <= maxMultiplier; i *= 2) {
            setHashList(i * BEGIN);

            for (int j = 0; j < MEASUREMENT_COUNT; j++) {
                measurement();
            }

            writer.println(average() + "\n");
            times.clear();
        }
    }
}
