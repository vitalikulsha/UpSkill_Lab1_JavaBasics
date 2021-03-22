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
}