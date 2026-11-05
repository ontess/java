//2. Создать класс Payment с внутренним классом, с помощью объектов
// которого можно сформировать покупку из нескольких товаров.
//Использовать внутренний класс и продемонстрировать использование анонимного класса!

package org.example;

public class Main {

    public static void main(String[] args) {

        Payment payment = new Payment();

        payment.addProduct("Laptop", 75000, 1);
        payment.addProduct("Mouse", 2500, 2);
        payment.addProduct("Keyboard", 4500, 1);

        payment.printPayment();

        Discount discount = new Discount() {

            @Override
            public double apply(double total) {
                return total * 0.9;
            }
        };

        double discountedTotal =
                discount.apply(payment.getTotal());

        System.out.println(
                "Total with 10% discount: "
                        + discountedTotal + " rub."
        );
    }
}