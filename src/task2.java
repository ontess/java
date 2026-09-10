//задание 16. Для каждого числа из заданной последовательности натуральных чисел найти
// произведение цифр, находящихся на чётных позициях (нумерация позиций идёт справа налево).
import java.util.Scanner;
public class task2 {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Введите кол-во чисел в последовательности");
                int count = scanner.nextInt();

                System.out.println("Введите сами числа через пробел, в конце Enter:");
                for (int i = 0; i < count; i++){
                    int number = scanner.nextInt();

                    int originalNumber = number;
                    int position = 1;
                    int multi = 1;

                    boolean hasPos = false;

                    while (number>0){
                        int digit = number%10;

                        if(position%2 == 0){
                            multi *=digit;
                            hasPos = true;
                        }

                        position++;
                        number /=10;
                    }

                    if(hasPos){
                        System.out.println("Для числа " + originalNumber + " произведение " + multi );
                    } else{
                        System.out.println("Для однозначного " + originalNumber + " мульти нету");
                    }


                }
                scanner.close();
            }
}