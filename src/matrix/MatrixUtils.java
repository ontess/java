package matrix;

import java.util.Random;
import java.util.Scanner;

public class MatrixUtils {


    public static int[][] generateRandomMatrix(int n) {
        Random random = new Random();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
        return matrix;
    }


    public static int[][] inputMatrix(Scanner scanner, int n) {
        int[][] matrix = new int[n][n];
        System.out.println("Введите элементы матрицы построчно (через пробел):");
        for (int i = 0; i < n; i++) {
            String[] row = scanner.nextLine().trim().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(row[j]);
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.printf("%4d", num);
            }
            System.out.println();
        }
    }
}