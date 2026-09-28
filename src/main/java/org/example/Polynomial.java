package org.example;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private final List<Complex> coefficients;

    public Polynomial(List<Complex> coefficients) {
        this.coefficients = new ArrayList<>(coefficients);
    }

    // Метод для сложения двух полиномов
    public Polynomial add(Polynomial other) {
        int maxSize = Math.max(this.coefficients.size(), other.coefficients.size());
        List<Complex> resultCoefficients = new ArrayList<>();

        for (int i = 0; i < maxSize; i++) {
            Complex coeff1 = i < this.coefficients.size() ? this.coefficients.get(i) : new Complex(0, 0);
            Complex coeff2 = i < other.coefficients.size() ? other.coefficients.get(i) : new Complex(0, 0);
            resultCoefficients.add(coeff1.add(coeff2));
        }

        return new Polynomial(resultCoefficients);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < coefficients.size(); i++) {
            Complex coeff = coefficients.get(i);

            if (coeff.getReal() == 0 && coeff.getImaginary() == 0) {
                continue;
            }

            if (sb.length() > 0) {
                sb.append(" + ");
            }

            if (coeff.getImaginary() == 0) {
                sb.append(coeff.getReal());
            } else if (coeff.getReal() == 0) {
                sb.append(coeff.getImaginary()).append("i");
            } else {
                sb.append("(")
                        .append(coeff.getReal())
                        .append(" + ")
                        .append(coeff.getImaginary())
                        .append("i)");
            }


            if (i == 1) {
                sb.append("x");
            } else if (i > 1) {
                sb.append("x^").append(i);
            }
        }

        if (sb.length() == 0) {
            return "0";
        }

        return sb.toString();
    }

    // Геттер для коэффициентов
    public List<Complex> getCoefficients() {
        return new ArrayList<>(coefficients);
    }
}