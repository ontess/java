package org.example.model;

public class GiftIncome extends Income {
    public enum GiftType {
        MONEY, PROPERTY
    }

    private final GiftType giftType;

    public GiftIncome(double amount, GiftType giftType) {
        super(amount);
        if (giftType == null) {
            throw new IllegalArgumentException("Не указан тип подарка");
        }
        this.giftType = giftType;
    }


    @Override
    public String getType() {
        return giftType == GiftType.MONEY ? "Денежный подарок" : "Подаренное имущество";
    }
}
