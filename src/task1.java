//задание 2 Определить, сколько раз каждая десятичная цифра встречается в записи
// каждого числа n с чётным количеством цифр из заданной последовательности натуральных чисел.
import java.util.Scanner;

public class task1 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите кол-во чисел в последовательности");
            int count = scanner.nextInt();

            System.out.println("Введите сами числа через пробел, в конце Enter:");
            for (int i = 0; i < count; i++) {
                int number = scanner.nextInt();

                int temp = number;
                int length = 0;

                while(temp > 0) {
                    length++;
                    temp = temp/10;
                }


                if(length % 2 == 0) {
                    System.out.println("Число подходит, длина чётная: " + length);
                    int[] counts = new int[10];

                    temp = number;

                    while (temp > 0) {
                        int lastDigit = temp % 10;
                        counts[lastDigit]++;
                        temp = temp / 10;
                    }
                    for (int j = 0; j < 10; j++) {
                        if (counts[j] > 0) {
                            System.out.println("  Цифра " + j + " встретилась " + counts[j] + " раз");
                        }
                    }
                }   else {
                        System.out.println("Число " + number + " пропущено (нечётная длина: " + length + ")");
                    }
                }

                scanner.close();

            }
        }
