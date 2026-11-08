package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PaymentTest {

    @Test
    public void testAddProduct() {

        Payment payment = new Payment();

        payment.addProduct("Laptop", 75000, 1);

        Assert.assertEquals(payment.getProductCount(), 1);
    }


    @Test
    public void testTotalPayment() {

        Payment payment = new Payment();

        payment.addProduct("Laptop", 75000, 1);
        payment.addProduct("Mouse", 2500, 2);
        payment.addProduct("Keyboard", 4500, 1);

        Assert.assertEquals(payment.getTotal(), 84500.0, 0.001);
    }

    @Test
    public void testProductTotalPrice() {

        Payment payment = new Payment();

        Payment.Product product =
                payment.addProduct("Mouse", 2500, 3);

        Assert.assertEquals(
                product.getTotalPrice(),
                7500.0,
                0.001
        );
    }

    @Test
    public void testNegativePrice() {

        Payment payment = new Payment();

        Assert.expectThrows(
                IllegalArgumentException.class,
                () -> payment.addProduct("Laptop", -100, 1)
        );
    }

    @Test
    public void testZeroQuantity() {

        Payment payment = new Payment();

        Assert.expectThrows(
                IllegalArgumentException.class,
                () -> payment.addProduct("Laptop", 100, 0)
        );
    }

    @Test
    public void testDiscount() {

        Payment payment = new Payment();

        payment.addProduct("Laptop", 1000, 1);

        Discount discount = new Discount() {

            @Override
            public double apply(double total) {
                return total * 0.9;
            }
        };

        Assert.assertEquals(
                discount.apply(payment.getTotal()),
                900.0,
                0.001
        );
    }

    @Test
    public void testProductsListIsImmutable() {

        Payment payment = new Payment();

        payment.addProduct("Laptop", 1000, 1);

        Assert.expectThrows(
                UnsupportedOperationException.class,
                () -> payment.getProducts().clear()
        );
    }
}