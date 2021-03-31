import org.junit.Assert;
import org.junit.Test;

public class QuadraticEquationTest {

    @Test
    public void calculate_numberAIsZero() {
        QuadraticEquation quadraticEquation = new QuadraticEquation(0, 4, 1);
        Assert.assertNull(quadraticEquation.calculate());
    }

    @Test
    public void calculate_discriminantNegative() {
        QuadraticEquation quadraticEquation = new QuadraticEquation(5, 1, 1);
        Assert.assertNull(quadraticEquation.calculate());
    }

    @Test
    public void calculate_discriminantZero() {
        QuadraticEquation quadraticEquation = new QuadraticEquation(2, 4, 2);
        Assert.assertArrayEquals(new double[]{-1}, quadraticEquation.calculate(), 0);
    }

    @Test
    public void calculate_discriminantPositive() {
        QuadraticEquation quadraticEquation = new QuadraticEquation(2, 4, 0);
        Assert.assertArrayEquals(new double[]{0, -2}, quadraticEquation.calculate(), 0);
    }
}