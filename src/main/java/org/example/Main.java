//2. Определить класс Полином с коэффициентами типа Комплексное число.
//Объявить массив/список/множество из m полиномов и определить сумму полиномов массива.


package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество полиномов (m): ");
        int m = scanner.nextInt();

        List<Polynomial> polynomials = new ArrayList<>();


        for (int i = 0; i < m; i++) {
            System.out.print("Введите степень полинома " + (i + 1) + " (максимальная степень): ");
            int degree = scanner.nextInt();

            List<Complex> coefficients = new ArrayList<>();
            for (int j = 0; j <= degree; j++) {
                System.out.print("Введите действительную часть коэффициента для x^" + j + ": ");
                double real = scanner.nextDouble();
                System.out.print("Введите мнимую часть коэффициента для x^" + j + ": ");
                double imaginary = scanner.nextDouble();
                coefficients.add(new Complex(real, imaginary));
            }
            polynomials.add(new Polynomial(coefficients));
        }


        Polynomial sum = PolynomialSum.sumPolynomials(polynomials);


        System.out.println("Сумма полиномов: " + sum);
    }
}