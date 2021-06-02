package Exercise_09._10;
/**********************************************
 *              QuadraticEquation             *
 **********************************************
 * - a: double                                *
 * - b: double                                *
 * - c: double                                *
 * ********************************************
 * + QuadraticEquation(double, double, double)*
 * + getDiscriminant(): double                *
 * + getRoot1(): double                       *
 * + getRoot2(): double                       *
 * ********************************************/

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getDiscriminant() {
        double d = (b * b) - (4 * a * c);
        return (d < 0) ? 0 : d;
    }

    public double getRoot1() {
        return (-b + Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
    }

    public double getRoot2() {
        return (-b - Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

}
