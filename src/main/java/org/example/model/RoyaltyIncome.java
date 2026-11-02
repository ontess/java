package org.example.model;

public class RoyaltyIncome extends Income {
    public RoyaltyIncome(double amount) {
        super(amount);
    }

    @Override
    public String getType() {
        return "Авторское вознаграждение";
    }
}
