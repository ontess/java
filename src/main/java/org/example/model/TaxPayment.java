package org.example.model;

public class TaxPayment {
    private final String source;
    private final double taxableAmount;
    private final double taxAmount;

    public TaxPayment(String source, double taxableAmount, double taxAmount) {
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("Источник налога не может быть пустым");
        }
        if (!Double.isFinite(taxableAmount) || taxableAmount < 0
                || !Double.isFinite(taxAmount) || taxAmount < 0) {
            throw new IllegalArgumentException("Суммы налога должны быть неотрицательными");
        }
        this.source = source;
        this.taxableAmount = taxableAmount;
        this.taxAmount = taxAmount;
    }


    public double getTaxableAmount() {
        return taxableAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    @Override
    public String toString() {
        return String.format("%s | налоговая база: %.2f | налог: %.2f",
                source, taxableAmount, taxAmount);
    }
}
