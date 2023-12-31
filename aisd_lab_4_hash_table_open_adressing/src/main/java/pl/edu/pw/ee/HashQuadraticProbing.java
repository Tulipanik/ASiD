package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    private double a = 0.5;
    private double b = 0.5;

    public HashQuadraticProbing() {
        super();
    }

    public HashQuadraticProbing(int size, double a, double b) {
        super(size);
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();

        int firstFunction = key % m;
        int hash = (int) ((firstFunction + a * i + b * i * i) % m);

        hash = hash < 0 ? -hash : hash;
        return hash;
    }
}
