import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuadraticEquationTest {
    QuadraticEquation quadraticEquation;

    @Before
    public void setQuadraticEquation() {
        this.quadraticEquation = new QuadraticEquation();
    }

    @Test
    public void calculate_numberAIsZero() {
        quadraticEquation.setNumberA(0);
        quadraticEquation.setNumberB(4);
        quadraticEquation.setNumberC(1);
        Assert.assertNull(quadraticEquation.calculate());
    }

    @Test
    public void calculate_discriminantNegative() {
        quadraticEquation.setNumberA(5);
        quadraticEquation.setNumberB(1);
        quadraticEquation.setNumberC(1);
        Assert.assertNull(quadraticEquation.calculate());
    }

    @Test
    public void calculate_discriminantZero() {
        quadraticEquation.setNumberA(2);
        quadraticEquation.setNumberB(4);
        quadraticEquation.setNumberC(2);
        Assert.assertArrayEquals(new double[]{-1}, quadraticEquation.calculate(), 0);
    }

    @Test
    public void calculate_discriminantPositive() {
        quadraticEquation.setNumberA(2);
        quadraticEquation.setNumberB(4);
        quadraticEquation.setNumberC(0);
        Assert.assertArrayEquals(new double[]{0, -2}, quadraticEquation.calculate(), 0);
    }
}