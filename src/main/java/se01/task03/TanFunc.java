package se01.task03;

public class TanFunc {

    public static void main(String[] args) {
        double a = -5.00;
        double b = 5.00;
        double h = 0.01;
        int n = (int)((b - a)/h) + 1;

        int i = 0;
        double[] f = new double[n];
        for (double x = a; x <= b; x += h) {
            f[i] = Math.tan(2 * x) - 3;
            System.out.printf("x = %.2f \t f(x) = %f\n", x, f[i]);
            i++;
        }
    }
}
