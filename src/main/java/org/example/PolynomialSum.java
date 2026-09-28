package org.example;

import java.util.List;

public class PolynomialSum {
    public static Polynomial sumPolynomials(List<Polynomial> polynomials) {
        if (polynomials.isEmpty()) {
            return new Polynomial(List.of(new Complex(0, 0)));
        }

        Polynomial result = polynomials.get(0);
        for (int i = 1; i < polynomials.size(); i++) {
            result = result.add(polynomials.get(i));
        }

        return result;
    }
}