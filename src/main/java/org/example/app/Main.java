package org.example.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.factory.IncomeFactory;
import org.example.factory.TaxPaymentFactory;
import org.example.model.ChildBenefit;
import org.example.model.Income;
import org.example.model.TaxPayment;
import org.example.reader.TaxDataReader;
import org.example.service.TaxService;

import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Path input = Path.of("data", "taxes.txt");

        try {
            TaxDataReader reader = new TaxDataReader(new IncomeFactory());
            List<Income> incomes = reader.readIncomes(input);
            ChildBenefit childBenefit = reader.readChildBenefit(input);

            TaxService service = new TaxService(new TaxPaymentFactory());
            List<TaxPayment> payments = service.calculateTaxes(incomes, childBenefit);
            List<TaxPayment> sortedPayments = service.sortByTaxAmount(payments);

            System.out.println("Налоговые выплаты по возрастанию суммы:");
            for (TaxPayment payment : sortedPayments) {
                System.out.println(payment);
            }
            System.out.printf("%nОбщая сумма налогов: %.2f%n", service.totalTax(payments));
            logger.info("Расчёт налогов завершён");
        } catch (Exception exception) {
            logger.error("Не удалось выполнить расчёт налогов", exception);
            System.err.println("Ошибка: " + exception.getMessage());
        }
    }
}
