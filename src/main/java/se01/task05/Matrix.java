package se01.task05;

public class Matrix {

    public static void main(String[] args) {
        final int M = 17;

        int[][] matrix = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (i != j && i != M-j-1) matrix[i][j] = 0;
                else matrix[i][j] = 1;
                System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println();
        }

    }
}
