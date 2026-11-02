package org.example.model;

public abstract class Income {
    private final double amount;

    protected Income(double amount) {
        if (!Double.isFinite(amount) || amount < 0) {
            throw new IllegalArgumentException("Сумма дохода должна быть неотрицательной");
        }
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public abstract String getType();
}