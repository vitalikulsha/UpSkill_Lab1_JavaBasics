import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        QuadraticEquation quadraticEquation = new QuadraticEquation(0, 6, 1);
        System.out.println("Quadratic equation = " + Arrays.toString(quadraticEquation.calculate()));
    }
}
