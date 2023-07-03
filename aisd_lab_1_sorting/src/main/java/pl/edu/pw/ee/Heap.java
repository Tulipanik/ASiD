package pl.edu.pw.ee;

import java.util.List;

import pl.edu.pw.ee.services.HeapExtension;
import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T>, HeapExtension {

    private List<T> data;

    public Heap(List<T> data) {
        this.data = data;
        build();
    }

    @Override
    public void put(T item) {

        if (data == null) {
            throw new ArrayIndexOutOfBoundsException
                    ("Cannot put item on null heap!");
        } else if (item == null) {
            throw new ArrayIndexOutOfBoundsException
                    ("Putted item cannot be null");
        }

        data.add(item);
        build();
    }

    @Override
    public T pop() {

        if (data == null || data.size() == 0) {
            throw new ArrayIndexOutOfBoundsException
                    ("Array cannot be null!");
        }

        T removed = data.get(0);
        data.set(0, data.get(data.size() - 1));
        data.remove(data.size() - 1);

        heapify(0, data.size());

        return removed;
    }

    @Override
    public void build() {

        if (data == null) {
            throw new ArrayIndexOutOfBoundsException
                    ("Array cannot be null!");
        }

        int n = data.size();

        for (int i = n; i >= 0; i--) {
            heapify(i, n);
        }
    }

    @Override
    public void heapify(int startId, int endId) {

        if (data == null) {
            throw new ArrayIndexOutOfBoundsException
                    ("Array cannot be null");
        }

        int parent = startId;
        int firstChild = 2 * parent + 1;
        int graterChild;

        while (firstChild < endId) {
            graterChild = firstChild;
            int secondChild = 2 * parent + 2;

            if (secondChild < endId && data.get(secondChild).compareTo(data.get(firstChild)) > 0) {
                graterChild = secondChild;
            }

            if (data.get(graterChild).compareTo(data.get(parent)) > 0) {
                T tmp = data.get(graterChild);
                data.set(graterChild, data.get(parent));
                data.set(parent, tmp);
                parent = graterChild;
                firstChild = 2 * parent + 1;

            } else {
                return;
            }
        }
    }
}
