public class QuadraticEquation {
    private double numbA;
    private double numbB;
    private double numbC;

    public QuadraticEquation(double numbA, double numbB, double numbC) {
        this.numbA = numbA;
        this.numbB = numbB;
        this.numbC = numbC;
    }

    public double[] calcQuadraticEquation() {
        double[] rootsEquation = new double[2];
        double result = Math.sqrt(Math.pow(numbB, 2) - 4 * numbA * numbC);
        rootsEquation[0] = (-numbB + result) / (2 * numbA);
        rootsEquation[1] = (-numbB - result) / (2 * numbA);
        return rootsEquation;
    }
}
