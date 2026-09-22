package matrix;

import java.util.stream.IntStream;

public class MatrixOperations {


    public static int[] findMinPositionWithStream(int[][] matrix) {
        return IntStream.range(0, matrix.length)
                .boxed()
                .flatMap(i -> IntStream.range(0, matrix.length)
                        .mapToObj(j -> new int[]{i, j, matrix[i][j]}))
                .min((a, b) -> Integer.compare(a[2], b[2]))
                .map(pos -> new int[]{pos[0], pos[1]})
                .orElse(new int[]{0, 0});
    }

    public static int[][] moveMinToPosition(int[][] matrix, int targetRow, int targetCol) {
        int[] minPos = findMinPositionWithStream(matrix);
        int minRow = minPos[0];
        int minCol = minPos[1];

        if (minRow != targetRow) {
            int[] temp = matrix[minRow];
            matrix[minRow] = matrix[targetRow];
            matrix[targetRow] = temp;
        }

        if (minCol != targetCol) {
            for (int i = 0; i < matrix.length; i++) {
                int temp = matrix[i][minCol];
                matrix[i][minCol] = matrix[i][targetCol];
                matrix[i][targetCol] = temp;
            }
        }

        return matrix;
    }
}