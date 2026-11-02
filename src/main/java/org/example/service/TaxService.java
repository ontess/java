package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.factory.TaxPaymentFactory;
import org.example.model.ChildBenefit;
import org.example.model.Income;
import org.example.model.TaxPayment;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class TaxService {
    private static final Logger logger = LogManager.getLogger(TaxService.class);
    private final TaxPaymentFactory taxPaymentFactory;

    public TaxService(TaxPaymentFactory taxPaymentFactory) {
        if (taxPaymentFactory == null) {
            throw new IllegalArgumentException("Фабрика налоговых выплат не может быть null");
        }
        this.taxPaymentFactory = taxPaymentFactory;
    }

    public List<TaxPayment> calculateTaxes(List<Income> incomes, ChildBenefit childBenefit) {
        if (incomes == null) {
            throw new IllegalArgumentException("Список доходов не может быть null");
        }

        List<TaxPayment> payments = new ArrayList<>();
        for (Income income : incomes) {
            if (income == null) {
                logger.warn("Пропущен пустой объект дохода");
                continue;
            }
            payments.add(taxPaymentFactory.create(income, childBenefit));
        }
        logger.info("Рассчитано налоговых выплат: {}", payments.size());
        return payments;
    }

    public double totalTax(List<TaxPayment> payments) {
        if (payments == null) {
            throw new IllegalArgumentException("Список налогов не может быть null");
        }
        return payments.stream().mapToDouble(TaxPayment::getTaxAmount).sum();
    }

    public List<TaxPayment> sortByTaxAmount(List<TaxPayment> payments) {
        if (payments == null) {
            throw new IllegalArgumentException("Список налогов не может быть null");
        }
        List<TaxPayment> sorted = new ArrayList<>(payments);
        sorted.sort(Comparator.comparingDouble(TaxPayment::getTaxAmount));
        return sorted;
    }
}
