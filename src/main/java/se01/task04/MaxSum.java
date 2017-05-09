package se01.task04;

import java.util.Random;

public class MaxSum {

    public static void main(String[] args) {
        int n = 109;
        double[] a = new double[2 * n];

        double rangeMin = -50.0;
        double rangeMax = 50.0;
        Random r = new Random();
        for (int i = 0; i < (2 * n); i++) {
            a[i] =  rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            System.out.printf("a[%d] = %.3f\n", i, a[i]);
        }

        int iMaxSum = 0, jMaxSum = 0;
        double max = a[0];
        for (int i = 0, j = (2 * n - 1); (i <= n) && (j >= n + 1); i++, j--) {
            if (a[i] + a[j] > max) {
                max = a[i] + a[j];
                iMaxSum = i;
                jMaxSum = j;
            }
        }
        System.out.printf("Максимальная сумма получилась из элементов a[%d] = %.3f и a[%d] = %.3f и равна %.3f.\n", iMaxSum, a[iMaxSum], jMaxSum, a[jMaxSum], max);
    }
}
