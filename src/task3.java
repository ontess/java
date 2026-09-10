//30.Найти все числа-палиндромы из заданной последовательности чисел
//при возведении которых в квадрат получают также числа-палиндромы.
// Число называется палиндромом, если его запись читается одинаково слева направо и справа налево,
//например, 12321.

import java.util.Scanner;
public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите кол-во чисел в последовательности");
        int count = scanner.nextInt();

        System.out.println("Введите сами числа через пробел, в конце Enter:");
        for (int i = 0; i < count; i++){

            long number = scanner.nextLong();

            long temp1 = number;
            long reversed = 0;

            while(temp1>0){
                long digit = temp1 % 10;
                reversed = reversed * 10 + digit;
                temp1 /= 10;
            }

            if(number==reversed){
                long multi = number*number;

                long temp2 = multi;
                long reversed2 = 0;
                while(temp2>0){
                    long digit = temp2 % 10;
                    reversed2 = reversed2 * 10 + digit;
                    temp2 /= 10;
                }
                if (multi == reversed2) {
                    System.out.println("Число: " + number + " Его квадрат: " + multi);
                }
            }

        }
        scanner.close();
        }
}