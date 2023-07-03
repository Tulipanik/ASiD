package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.lang.reflect.Array;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem nil = null;
    private Elem[] hashElems;
    private int nElem;

    private class Elem {

        private T value;
        private Elem next;

        Elem(T value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }
    }

    @SuppressWarnings("unchecked")
    public HashListChaining(int size) {

        if (size <= 0) {
            throw new IllegalArgumentException
                    ("Hash Size have to be grater than 0");
        }

        hashElems = (Elem[]) Array.newInstance(Elem.class, size);
        initializeHash();
    }

    @Override
    public void add(T value) {

        if (value == null) {
            throw new IllegalArgumentException
                    ("Added value cannot be null!");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems[hashId];
        while (oldElem != nil && !oldElem.value.equals(value)) {
            oldElem = oldElem.next;
        }

        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems[hashId] = new Elem(value, hashElems[hashId]);
            nElem++;
        }
    }

    @Override
    public Object get(T value) {

        if (value == null) {
            throw new IllegalArgumentException
                    ("Value cannot be null!");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);
        Elem elem = hashElems[hashId];

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : nil;
    }

    @Override
    public void delete(T value) {

        if (value == null) {
            throw new IllegalArgumentException
                    ("Deleted Argument cannot be null");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);
        Elem elem = hashElems[hashId];
        Elem before = new Elem(null, elem);
        boolean isFirst = true;

        while (elem != nil && !elem.value.equals(value)) {
            if (isFirst) {
                isFirst = false;
            }
            before = elem;
            elem = elem.next;
        }

        if (elem == nil) {
            throw new IllegalArgumentException
                    ("Value not exist in hashList!");
        } else if (elem.next == nil) {
            if (isFirst) {
                hashElems[hashId] = nil;
                return;
            }

            before.next = nil;
            elem.value = null;

        } else {
            if (isFirst) {
                hashElems[hashId] = elem.next;
            }

            before.next = elem.next;
        }
    }

    public double countLoadFactor() {

        double size = hashElems.length;
        return nElem / size;
    }

    private void initializeHash() {

        int n = hashElems.length;

        for (int i = 0; i < n; i++) {
            hashElems[i] = nil;
        }
    }

    private int countHashId(int hashCode) {

        int n = hashElems.length;
        return Math.abs(hashCode) % n;
    }

    public int getHashId(int hashCode) {

        return countHashId(hashCode);
    }

    public int hashElemsLength() {

        return hashElems.length;
    }

    public Elem[] hashElemArray() {

        return hashElems;
    }

    public Elem getNext(Elem element) {

        return element.next;
    }

    public T getValue(Elem element) {

        return element.value;
    }

    public Elem getElem(int index) {

        return hashElems[index];
    }


}
