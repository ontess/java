package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Payment {

    private static final Logger logger =
            LogManager.getLogger(Payment.class);

    private final List<Product> products = new ArrayList<>();

    public static class Product {

        private final String name;
        private final double price;
        private final int quantity;

        private Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }


        public double getTotalPrice() {
            return price * quantity;
        }

        @Override
        public String toString() {
            return name + " | " + price + " руб. | "
                    + quantity + " шт. | "
                    + getTotalPrice() + " руб.";
        }
    }

    public Product addProduct(String name, double price, int quantity) {

        Objects.requireNonNull(name, "Product name must not be null");

        if (name.isBlank()) {
            logger.warn("Product name is blank");
            throw new IllegalArgumentException(
                    "Product name must not be blank");
        }

        if (!Double.isFinite(price) || price <= 0) {
            logger.warn("Invalid product price: {}", price);
            throw new IllegalArgumentException(
                    "Product price must be positive and finite");
        }

        if (quantity <= 0) {
            logger.warn("Invalid product quantity: {}", quantity);
            throw new IllegalArgumentException(
                    "Product quantity must be positive");
        }

        Product product = new Product(name, price, quantity);

        products.add(product);

        logger.info("Product added: {}", product);

        return product;
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }


    public double getTotal() {

        double total = 0;

        for (Product product : products) {
            total += product.getTotalPrice();
        }

        logger.debug("Total payment: {}", total);

        return total;
    }

    public int getProductCount() {
        return products.size();
    }

    public void printPayment() {

        System.out.println("Purchase:");

        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println("Total: " + getTotal() + " rub.");

        logger.info("Payment printed. Total: {}", getTotal());
    }
}