import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        QuadraticEquation quadraticEquation = new QuadraticEquation(1.0, 0, 0);
        System.out.println("Quadratic equation = " + Arrays.toString(quadraticEquation.calcQuadraticEquation()));
    }
}
