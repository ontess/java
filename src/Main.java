import matrix.*;
import utils.InputUtils;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int n = InputUtils.inputInt(scanner, "Введите размерность матрицы n: ");


        boolean isRandom = InputUtils.inputBoolean(scanner, "Заполнить матрицу случайными числами?");
        int[][] matrix;
        if (isRandom) {
            matrix = MatrixUtils.generateRandomMatrix(n);
        } else {
            matrix = MatrixUtils.inputMatrix(scanner, n);
        }

        System.out.println("Исходная матрица:");
        MatrixUtils.printMatrix(matrix);

        //Задание 2
        System.out.println("Циклический сдвиг ");
        int k = InputUtils.inputInt(scanner, "Введите количество позиций для сдвига (k): ");
        String direction = scanner.nextLine().trim().toLowerCase();
        System.out.print("Направление сдвига (right/left/up/down): ");
        direction = scanner.nextLine().trim().toLowerCase();

        int[][] shiftedMatrix = switch (direction) {
            case "right" -> MatrixShifter.shiftRight(matrix, k);
            case "left" -> MatrixShifter.shiftLeft(matrix, k);
            case "up" -> MatrixShifter.shiftUp(matrix, k);
            case "down" -> MatrixShifter.shiftDown(matrix, k);
            default -> {
                System.out.println("Некорректное направление. Используем сдвиг вправо.");
                yield MatrixShifter.shiftRight(matrix, k);
            }
        };

        System.out.println("Матрица после сдвига:");
        MatrixUtils.printMatrix(shiftedMatrix);

        //Задание 16:
        System.out.println("Перемещение минимального элемента ");
        int targetRow = InputUtils.inputInt(scanner, "Введите целевую строку для перемещения минимального элемента: ");
        int targetCol = InputUtils.inputInt(scanner, "Введите целевой столбец для перемещения минимального элемента: ");

        int[][] movedMatrix = MatrixOperations.moveMinToPosition(shiftedMatrix, targetRow, targetCol);
        System.out.println("Матрица после перемещения минимального элемента:");
        MatrixUtils.printMatrix(movedMatrix);

        scanner.close();
    }
}