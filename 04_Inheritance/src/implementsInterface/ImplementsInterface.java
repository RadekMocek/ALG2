package implementsInterface;

import java.util.ArrayList;

public class ImplementsInterface {

    static ArrayList<Computable> shapes;

    public static void main(String[] args) {
        shapes = new ArrayList();
        shapes.add(new Circle(1));
        shapes.add(new Rectangle(2, 3));

        double sum = 0;
        for (Computable shape : shapes) {
            sum = sum + shape.area();
        }
        System.out.println(sum);

        double sum1 = 0;
        for (Computable shape : shapes) {
            sum1 = sum1 + shape.circum();
        }

        System.out.println(sum1);
    }

}
