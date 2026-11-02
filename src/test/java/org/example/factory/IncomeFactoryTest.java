package org.example.factory;

import org.example.model.EmploymentIncome;
import org.example.model.RoyaltyIncome;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IncomeFactoryTest {
    private final IncomeFactory factory = new IncomeFactory();

    @Test
    public void createsEmploymentIncome() {
        var income = factory.create(new String[]{"EMPLOYMENT", "10000", "MAIN"});
        Assert.assertTrue(income instanceof EmploymentIncome);
        Assert.assertEquals(income.getAmount(), 10000.0, 0.001);
    }

    @Test
    public void createsRoyaltyIncome() {
        var income = factory.create(new String[]{"ROYALTY", "2000"});
        Assert.assertTrue(income instanceof RoyaltyIncome);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void rejectsNegativeAmount() {
        factory.create(new String[]{"ROYALTY", "-5"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void rejectsUnknownType() {
        factory.create(new String[]{"UNKNOWN", "100"});
    }
}