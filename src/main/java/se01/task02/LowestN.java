package se01.task02;

public class LowestN {
    public static void main(String[] args) {
        final double E = 0.0001;
        final int n = 1000;

        double[] a = new double[n];
        boolean lowestIndexFound = false;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            a[i] = 1 / Math.pow(i+ 1, 2);
            result.append("a[");
            result.append(i);
            result.append("] = ");
            result.append(a[i]);
            System.out.println(result);
            result.setLength(0);
            if (a[i] < E) {
                lowestIndexFound = true;
                System.out.println("\nНаименьший номер n, удовлетворяющий условию \"M: a[n] < E\", равен " + i);
                break;
            }
        }

        if (!lowestIndexFound)
            System.out.println("Наименьшего номера n, удовлетворяющего условию \"M: a[n] < E\", не существует.");
    }
}
