package org.example.model;

public class PropertySaleIncome extends Income {
    public PropertySaleIncome(double amount) {
        super(amount);
    }

    @Override
    public String getType() {
        return "Продажа имущества";
    }
}
