package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.util.NoSuchElementException;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;
    private T firstElem;
    private boolean checkIfNotLoop;

    private static class DummyElement<T extends Comparable<T>> implements Comparable<T> {
        @Override
        public int compareTo(T o) {
            return 0;
        }
    }

    private final T dummyElement = (T) new DummyElement<>();

    HashOpenAdressing() {
        this(2039);
    }

    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();
        validateNumOfElems();

        int key = newElem.hashCode();
        int i = 0;
        int position = -1;
        int hashId = hashFunc(key, i);
        firstElem = hashElems[hashId];

        while (hashElems[hashId] != nil && hashElems[hashId] != newElem) {
            if (i + 1 == size) {
                doubleResize();
                i = -1;
            }
            if (hashElems[hashId].equals(dummyElement) && position == -1) {
                position = hashId;
            }

            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }

        if (newElem.equals(hashElems[hashId])) {
            hashElems[hashId] = newElem;
            return;
        } else if (position != -1) {
            hashId = position;
        }

        hashElems[hashId] = newElem;
        nElems++;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);
        checkIfNotLoop = false;
        firstElem = hashElems[hashId];

        while (!elem.equals(hashElems[hashId]) && hashElems[hashId] != nil) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);

            if (firstElem.equals(hashElems[hashId])) {
                checkIfNotLoop = true;
                break;
            }
        }

        if (hashElems[hashId] == nil || checkIfNotLoop) {
            throw new NoSuchElementException
                    ("There's no such an element in hash table!");
        }

        return hashElems[hashId];
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);
        checkIfNotLoop = false;
        firstElem = hashElems[hashId];

        while (!elem.equals(hashElems[hashId])
                && hashElems[hashId] != nil) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);

            if (firstElem.equals(hashElems[hashId])) {
                checkIfNotLoop = true;
                break;
            }
        }

        if (hashElems[hashId] == nil || checkIfNotLoop) {
            throw new NoSuchElementException
                    ("There's no such an element in hash table!");
        }

        hashElems[hashId] = dummyElement;
        nElems = nElems - 1;

    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException
                    ("Initial size of hash table cannot be lower than 1!");
        } else if (initialSize == 3 && this instanceof HashDoubleHashing) {
            throw new IllegalArgumentException
                    ("Initial size of hash table with Double hashing cannot be 3!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException
                    ("Input elem cannot be null!");
        }
    }

    private void validateNumOfElems() {
        int maxNumOfElems = 100000000;

        if (nElems >= maxNumOfElems) {
            throw new IllegalArgumentException
                    ("Too many elements in hash!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    private void doubleResize() {
        this.size *= 2;

        T[] oldElems = hashElems;
        hashElems = (T[]) new Comparable[this.size];
        nElems = 0;

        for (T elem : oldElems) {
            if (elem != nil && !(elem instanceof DummyElement)) {
                put(elem);
            }
        }
    }

    public T getElementOnAPosition(int position) {
        if (position < 0 && position >= size) {
            return null;
        }

        return hashElems[position];
    }
}
