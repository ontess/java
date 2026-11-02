package org.example.factory;

import org.example.model.*;

public class TaxPaymentFactory {
    private static final double GENERAL_RATE = 0.13;
    private static final double PROPERTY_SALE_RATE = 0.13;
    private static final double MATERIAL_AID_EXEMPT_AMOUNT = 4000.0;
    private static final double CHILD_DEDUCTION_PER_CHILD = 1400.0;

    public TaxPayment create(Income income, ChildBenefit childBenefit) {
        if (income == null) {
            throw new IllegalArgumentException("Доход не может быть null");
        }

        double taxableAmount = income.getAmount();
        double rate = GENERAL_RATE;

        if (income instanceof PropertySaleIncome) {
            rate = PROPERTY_SALE_RATE;
        } else if (income instanceof MaterialAidIncome) {
            taxableAmount = Math.max(0, taxableAmount - MATERIAL_AID_EXEMPT_AMOUNT);
        }

        if (income instanceof EmploymentIncome
                && ((EmploymentIncome) income).getEmploymentType()
                == EmploymentIncome.EmploymentType.MAIN
                && childBenefit != null) {
            double deduction = childBenefit.getChildrenCount() * CHILD_DEDUCTION_PER_CHILD;
            taxableAmount = Math.max(0, taxableAmount - deduction);
        }

        double tax = taxableAmount * rate;
        return new TaxPayment(income.getType(), taxableAmount, tax);
    }
}
