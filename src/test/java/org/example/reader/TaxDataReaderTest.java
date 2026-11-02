package org.example.reader;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.factory.IncomeFactory;

import java.nio.file.Files;
import java.nio.file.Path;

public class TaxDataReaderTest {
    @Test
    public void ignoresInvalidLines() throws Exception {
        Path file = Files.createTempFile("tax-test", ".txt");
        try {
            Files.writeString(file, "ROYALTY;1000\nROYALTY;abc\nUNKNOWN;50\nEMPLOYMENT;200;MAIN\n");
            TaxDataReader reader = new TaxDataReader(new IncomeFactory());
            var incomes = reader.readIncomes(file);
            Assert.assertEquals(incomes.size(), 2);
        } finally {
            Files.deleteIfExists(file);
        }
    }

    @Test
    public void defaultsInvalidChildBenefitToZero() throws Exception {
        Path file = Files.createTempFile("tax-child-test", ".txt");
        try {
            Files.writeString(file, "CHILD_BENEFIT;-2\n");
            TaxDataReader reader = new TaxDataReader(new IncomeFactory());
            Assert.assertEquals(reader.readChildBenefit(file).getChildrenCount(), 0);
        } finally {
            Files.deleteIfExists(file);
        }
    }
}
