package entities;

public class Pair<T> {

    private T element1;
    private T element2;

    public T getElement1() {
        return element1;
    }

    public void setElement1(T element1) {
        this.element1 = element1;
    }

    public T getElement2() {
        return element2;
    }

    public void setElement2(T element2) {
        this.element2 = element2;
    }

    public Pair(T element1, T element2) {
        this.element1 = element1;
        this.element2 = element2;
    }
}
