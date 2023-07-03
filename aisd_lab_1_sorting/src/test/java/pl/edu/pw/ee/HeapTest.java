package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HeapTest {

    private Heap<Double> heap;
    private List<Double> data;
    final private double precision = 0.001;

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void buildingOnNullArray() {
        //given
        data = null;
        heap = new Heap<>(data);
        //when
        heap.build();
        //then
        assert false;
    }

    @Test
    public void buildingOnArrayWithLengthZero() {
        //given
        data = new ArrayList<>();
        heap = new Heap<>(data);
        //when
        heap.build();
        //then
        assert true;
    }

    @Test
    public void buildAnHeapWithAscendingArray() {
        //given
        data = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            data.add((double) i);
        }

        heap = new Heap<>(data);
        //when
        heap.build();
        //then
        for (int i = 0; i < data.size(); i++) {
            int child = 2 * i + 1;

            assert child >= data.size() || data.get(i) >= data.get(child);

            child += 1;

            assert child >= data.size() || data.get(i) >= data.get(child);
        }

        assert true;
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void tryingToPopItemFromNullArray() {
        //given
        data = null;
        heap = new Heap<>(data);
        //when
        heap.pop();
        //then
        assert false;
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void theLengthOfArrayIsZero() {
        //given
        data = new ArrayList<>();
        heap = new Heap<>(data);
        //when
        heap.pop();
        //then
        assert false;
    }

    @Test
    public void poppingTheGraterNumberIsOnTheTopOfTheHeap() {
        //given
        data = new ArrayList<>();

        for (int i = 10; i >= 0; i--) {
            data.add((double) i);
        }

        heap = new Heap<>(data);
        //when
        double result = heap.pop();
        //then
        double expected = 10;
        Assert.assertEquals(expected, result, precision);
    }

    @Test
    public void poppingTheSmallestNumberIsOnTheTopOfTheHeap() {
        //given
        data = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            data.add((double) i);
        }

        heap = new Heap<>(data);
        //when
        double result = heap.pop();
        //then
        double expected = 9;
        Assert.assertEquals(expected, result, precision);
    }

    @Test
    public void checkingIfHeapAfterPop() {
        //given
        data = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            data.add((double) i);
        }

        heap = new Heap<>(data);
        //when
        heap.pop();
        //then
        for (int i = 0; i < data.size(); i++) {
            int child = 2 * i + 1;

            assert child >= data.size() || data.get(i) >= data.get(child);

            child += 1;

            assert child >= data.size() || data.get(i) >= data.get(child);
        }

        assert true;
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void puttingNullItemOnHeap() {
        //given
        data = new ArrayList<>();

        for (int i = 4; i >= 0; i--) {
            data.add((double) i);
        }

        heap = new Heap<>(data);
        //when
        Double puttedItem = null;
        heap.put(puttedItem);
        //then
        assert false;
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void puttingItemOnNullHeap() {
        //given
        data = null;
        heap = new Heap<>(data);
        //when
        double puttedItem = 1.5;
        heap.put(puttedItem);
        //then
        assert false;
    }

    @Test
    public void puttingItemOnEmptyHeap() {
        //given
        data = new ArrayList<>();
        heap = new Heap<>(data);
        //when
        double puttedItem = 1.5;
        heap.put(puttedItem);
        //then
        Assert.assertEquals(puttedItem, data.get(0), precision);
    }

    @Test
    public void puttingTheSmallestItemOnHeap() {
        //given
        data = new ArrayList<>();

        for (int i = 4; i >= 0; i--) {
            data.add((double) i);
        }

        heap = new Heap<>(data);
        //when
        double puttedItem = -0.5;
        heap.put(puttedItem);
        //then
        Assert.assertEquals(puttedItem, data.get(data.size() - 1), precision);
    }

    @Test
    public void puttingTheGraterItemOnHeap() {
        //given
        data = new ArrayList<>();

        for (int i = 4; i >= 0; i--) {
            data.add((double) i);
        }

        heap = new Heap<>(data);
        //when
        double puttedItem = 5;
        heap.put(puttedItem);
        //then
        Assert.assertEquals(puttedItem, data.get(0), precision);
    }
}
