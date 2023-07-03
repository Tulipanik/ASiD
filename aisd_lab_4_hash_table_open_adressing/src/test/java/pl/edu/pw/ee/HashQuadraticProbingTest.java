package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.services.HashTable;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HashQuadraticProbingTest {


    AccessMethods methods = new AccessMethods();
    private final double a = 0.5;
    private final double b = 0.5;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInitialSizeISLessThanZero() {
        //given
        int initialSize = -1;
        //when
        HashTable<String> hash = new HashQuadraticProbing<>(initialSize, a, b);
        //then
        assert false;
    }

    @Test
    public void shouldCorrectlyAddNewElemsWhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashQuadraticProbing<>();

        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = methods.getNumOfElems(emptyHash);

        emptyHash.put(newEleme);

        int nOfElemsAfterPut = methods.getNumOfElems(emptyHash);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test
    public void shouldLeftOnlyOneElementInHashWhenExistingInHash() {
        //given
        HashTable<String> hash = new HashQuadraticProbing<>();
        String newElem = "nothing special";
        //when
        hash.put(newElem);
        hash.put(newElem);
        //then
        int expectedNumOfElems = 1;

        assertEquals(expectedNumOfElems, methods.getNumOfElems(hash));
    }

    @Test
    public void shouldDoubleTheSizeIfPuttingMoreElementsThanSize() {
        //given
        int initialSize = 1;

        HashTable<String> hash = new HashQuadraticProbing<>(initialSize, a, b);

        String newElem1 = "first element";
        String newElem2 = "second element";
        //when
        hash.put(newElem1);
        hash.put(newElem2);
        //when
        int expectedSize = 2;

        assertEquals(expectedSize, methods.getSize(hash));
    }

    @Test
    public void shouldNotChangeNumOfElemsWhenPuttingSameElementEvenWhenIsDummyBefore() {
        //given
        int elemsInHash = 2;

        HashTable<String> hash = new HashQuadraticProbing<>();
        String[] array = methods.generateChainsWithCollisionsOnFirst(methods.getSize(hash), elemsInHash);

        for (String i : array) {
            hash.put(i);
        }

        hash.delete(array[0]);
        //when
        hash.put(array[1]);
        //then
        int expectedNumOfElems = elemsInHash - 1;

        assertEquals(expectedNumOfElems, methods.getNumOfElems(hash));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowAnExceptionIfElementNotExistInHash() {
        //given
        HashTable<String> hash = new HashQuadraticProbing<>();

        String Elem = "nothing special";
        //when
        hash.get(Elem);
        //then
        assert false;
    }

    @Test
    public void lookingForElementWhichExistsInHashAsFirst() {
        //given
        HashTable<String> hash = new HashQuadraticProbing<>();

        String elem = "nothing special";

        hash.put(elem);
        //when
        String got = hash.get(elem);
        //then
        assertEquals(elem, got);
    }

    @Test(expected = NoSuchElementException.class)
    public void deletingNotExistingElementFromHash() {
        //given
        HashTable<String> hash = new HashQuadraticProbing<>();

        String elem = "nothing special";
        //when
        hash.delete(elem);
        //then
        assert false;
    }

    @Test
    public void deletingExistingArgumentFromHashAsFirst() {
        //given
        HashTable<String> hash = new HashQuadraticProbing<>();

        String elem = "nothing special";

        hash.put(elem);
        //when
        hash.delete(elem);
        //then
        int expectedSize = 0;

        assertEquals(expectedSize, methods.getNumOfElems(hash));
    }

    @Test
    public void deletingMiddleElementFromHashWithElementsWithSameHash() {
        //given
        int elemsInHash = 20;
        int size = 100;

        HashTable<String> hash = new HashQuadraticProbing<>(size, a, b);
        String[] array = methods.generateChainsWithCollisionsOnFirst(methods.getSize(hash), elemsInHash);

        for (String i : array) {
            hash.put(i);
        }
        //when
        hash.delete(array[array.length - 1]);
        //then
        int expectedNumOfElems = elemsInHash - 1;
        assertEquals(expectedNumOfElems, methods.getNumOfElems(hash));
    }

    @Test
    public void gettingSomethingAfterDummyElement() {
        //given
        int elemsInHash = 2;

        HashTable<String> hash = new HashQuadraticProbing<>();
        String[] array = methods.generateChainsWithCollisionsOnFirst(methods.getSize(hash), elemsInHash);

        for (String i : array) {
            hash.put(i);
        }

        hash.delete(array[0]);
        //when
        hash.get(array[1]);
        //then
        assert true;
    }

    @Test
    public void tryingToPutSomethingOnDummyElement() {
        //given
        int elemsInHash = 2;

        HashQuadraticProbing<String> hash = new HashQuadraticProbing<>();
        String[] array = methods.generateChainsWithCollisionsOnFirst(methods.getSize(hash), elemsInHash);

        for (String i : array) {
            hash.put(i);
        }

        hash.delete(array[0]);

        int hashId = hash.hashFunc(array[0].hashCode(), 0);

        assertNotEquals(array[0], hash.getElementOnAPosition(hashId));
        //when
        hash.put(array[0]);
        //then
        int expectedNumOfElems = 2;

        assertEquals(array[0], hash.getElementOnAPosition(hashId));
        assertEquals(expectedNumOfElems, methods.getNumOfElems(hash));
    }

    @Test(expected = IllegalArgumentException.class)
    public void puttingNullElement() {
        //given
        HashTable<String> hash = new HashQuadraticProbing<>();
        String newElem = null;
        //when
        hash.put(newElem);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void gettingNullElement() {
        //given
        HashTable<String> hash = new HashQuadraticProbing<>();
        String newElem = null;
        //when
        hash.get(newElem);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void deletingNullElement() {
        //given
        HashTable<String> hash = new HashQuadraticProbing<>();
        String newElem = null;
        //when
        hash.delete(newElem);
        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingLotsOfElements() {
        //given
        int numOfElems = 100000001;
        HashTable<Integer> hash = new HashQuadraticProbing<>();
        //when
        for (int i = 0; i < numOfElems; i++) {
            hash.put(i);
        }
        //then
        assert false;
    }
}
