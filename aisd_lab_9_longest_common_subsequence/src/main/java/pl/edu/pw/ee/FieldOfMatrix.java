
package pl.edu.pw.ee;

public class FieldOfMatrix {

    int direction;
    int value;
    int indexLeft;
    int indexTop;

    public FieldOfMatrix(int direction, int value, int indexTop, int indexLeft) {
        this.direction = direction;
        this.value = value;
        this.indexLeft = indexLeft;
        this.indexTop = indexTop;
    }

    public int getValue() {
        return value;
    }

    public int getDirection() {
        return direction;
    }

    public int getIndexLeft() {
        return indexLeft;
    }

    public int getIndexTop() {
        return indexTop;
    }
}