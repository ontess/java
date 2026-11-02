package org.example.service;

import org.example.model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.factory.TaxPaymentFactory;

import java.util.List;

public class TaxServiceTest {
    private final TaxService service = new TaxService(new TaxPaymentFactory());

    @Test
    public void calculatesRoyaltyTax() {
        List<TaxPayment> payments = service.calculateTaxes(
                List.of(new RoyaltyIncome(10000)), new ChildBenefit(0));
        Assert.assertEquals(payments.get(0).getTaxAmount(), 1300.0, 0.001);
    }

    @Test
    public void appliesChildDeductionToMainEmployment() {
        List<TaxPayment> payments = service.calculateTaxes(
                List.of(new EmploymentIncome(10000, EmploymentIncome.EmploymentType.MAIN)),
                new ChildBenefit(2));
        Assert.assertEquals(payments.getFirst().getTaxableAmount(), 7200.0, 0.001);
        Assert.assertEquals(payments.getFirst().getTaxAmount(), 936.0, 0.001);
    }

    @Test
    public void calculatesTotalTax() {
        List<TaxPayment> payments = List.of(
                new TaxPayment("A", 1000, 100),
                new TaxPayment("B", 2000, 250));
        Assert.assertEquals(service.totalTax(payments), 350.0, 0.001);
    }

    @Test
    public void skipsNullIncome() {
        List<Income> incomes = new java.util.ArrayList<>();
        incomes.add(new RoyaltyIncome(1000));
        incomes.add(null);
        Assert.assertEquals(service.calculateTaxes(incomes, new ChildBenefit(0)).size(), 1);
    }
}
