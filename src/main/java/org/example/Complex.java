//2. Определить класс Полином с коэффициентами типа Комплексное число.
//Объявить массив/список/множество из m полиномов и определить сумму полиномов массива.


package org.example;

public class Complex {

    private double real;
    private double imaginary;

    public Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex add(Complex other){
        return new Complex(
                this.real + other.real,
                this.imaginary + other.imaginary
        );
    }
    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    @Override
    public String toString() {
        if (imaginary >= 0) {
            return real + " + " + imaginary + "i";
        } else {
            return real + " - " + (-imaginary) + "i";
        }
    }
}