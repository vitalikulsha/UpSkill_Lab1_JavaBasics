public class QuadraticEquation {
    private double numberA;
    private double numberB;
    private double numberC;

    public QuadraticEquation() {
    }

    public QuadraticEquation(double numberA, double numberB, double numberC) {
        this.numberA = numberA;
        this.numberB = numberB;
        this.numberC = numberC;
    }

    //Calculate quadratic equation ax2+bx+c=0
    public double[] calculate() {
        double[] rootsEquation;
        double discriminant = (numberB * numberB) - (4 * numberA * numberC);
        if (discriminant > 0 && numberA != 0) {
            rootsEquation = new double[2];
            double result = Math.sqrt((numberB * numberB) - (4 * numberA * numberC));
            rootsEquation[0] = (-numberB + result) / (2 * numberA);
            rootsEquation[1] = (-numberB - result) / (2 * numberA);
        } else if (discriminant == 0 && numberA != 0) {
            rootsEquation = new double[1];
            rootsEquation[0] = -numberB / (2 * numberA);
        } else {
            System.out.println("No roots of the equation!");
            rootsEquation = null;
        }
        return rootsEquation;
    }

    public double getNumberA() {
        return numberA;
    }

    public void setNumberA(double numberA) {
        this.numberA = numberA;
    }

    public double getNumberB() {
        return numberB;
    }

    public void setNumberB(double numberB) {
        this.numberB = numberB;
    }

    public double getNumberC() {
        return numberC;
    }

    public void setNumberC(double numberC) {
        this.numberC = numberC;
    }
}
