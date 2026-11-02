package org.example.model;

public class ForeignTransferIncome extends Income {
    public ForeignTransferIncome(double amount) {
        super(amount);
    }

    @Override
    public String getType() {
        return "Перевод из-за границы";
    }
}
