package org.example.model;

public class MaterialAidIncome extends Income {
    public MaterialAidIncome(double amount) {
        super(amount);
    }

    @Override
    public String getType() {
        return "Материальная помощь";
    }
}
