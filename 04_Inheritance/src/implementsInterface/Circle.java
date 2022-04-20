package implementsInterface;

public class Circle implements Computable {

    private int r;

    public Circle(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Override
    public double area() {
        return Math.PI * r * r;
    }

    @Override
    public double circum() {
        return 2 * Math.PI * r;
    }
}
