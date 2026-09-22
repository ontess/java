package matrix;

import java.util.Arrays;

public class MatrixShifter {


    public static int[][] shiftRight(int[][] matrix, int k) {
        int newK = k % matrix.length;
        return Arrays.stream(matrix)
                .map(row -> {
                    int[] newRow = new int[row.length];
                    System.arraycopy(row, row.length - newK, newRow, 0, newK);
                    System.arraycopy(row, 0, newRow, newK, row.length - newK);
                    return newRow;
                })
                .toArray(int[][]::new);
    }


    public static int[][] shiftLeft(int[][] matrix, int k) {
        int newK = k % matrix.length;
        return Arrays.stream(matrix)
                .map(row -> {
                    int[] newRow = new int[row.length];
                    System.arraycopy(row, newK, newRow, 0, row.length - newK);
                    System.arraycopy(row, 0, newRow, row.length - newK, newK);
                    return newRow;
                })
                .toArray(int[][]::new);
    }


    public static int[][] shiftDown(int[][] matrix, int k) {
        int newK = k % matrix.length;
        int[][] newMatrix = new int[matrix.length][matrix.length];
        for (int j = 0; j < matrix.length; j++) {
            int[] column = new int[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                column[i] = matrix[i][j];
            }

            int[] shiftedColumn = new int[column.length];
            System.arraycopy(column, column.length - newK, shiftedColumn, 0, newK);
            System.arraycopy(column, 0, shiftedColumn, newK, column.length - newK);

            for (int i = 0; i < matrix.length; i++) {
                newMatrix[i][j] = shiftedColumn[i];
            }
        }
        return newMatrix;
    }

    public static int[][] shiftUp(int[][] matrix, int k) {
        int newK = k % matrix.length;
        int[][] newMatrix = new int[matrix.length][matrix.length];
        for (int j = 0; j < matrix.length; j++) {
            int[] column = new int[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                column[i] = matrix[i][j];
            }

            int[] shiftedColumn = new int[column.length];
            System.arraycopy(column, newK, shiftedColumn, 0, column.length - newK);
            System.arraycopy(column, 0, shiftedColumn, column.length - newK, newK);

            for (int i = 0; i < matrix.length; i++) {
                newMatrix[i][j] = shiftedColumn[i];
            }
        }
        return newMatrix;
    }
}