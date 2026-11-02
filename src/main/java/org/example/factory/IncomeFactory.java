package org.example.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.*;

public class IncomeFactory {
    private static final Logger logger = LogManager.getLogger(IncomeFactory.class);

    public Income create(String[] fields) {
        if (fields == null || fields.length < 2) {
            throw new IllegalArgumentException("Недостаточно полей в записи");
        }

        String type = fields[0].trim().toUpperCase();
        double amount = parseAmount(fields[1]);

        return switch (type) {
            case "EMPLOYMENT" -> {
                if (fields.length < 3) {
                    throw new IllegalArgumentException("Для EMPLOYMENT нужен параметр MAIN или ADDITIONAL");
                }
                EmploymentIncome.EmploymentType employmentType =
                        EmploymentIncome.EmploymentType.valueOf(fields[2].trim().toUpperCase());
                yield new EmploymentIncome(amount, employmentType);
            }
            case "ROYALTY" -> new RoyaltyIncome(amount);
            case "PROPERTY_SALE" -> new PropertySaleIncome(amount);
            case "GIFT_MONEY" -> new GiftIncome(amount, GiftIncome.GiftType.MONEY);
            case "GIFT_PROPERTY" -> new GiftIncome(amount, GiftIncome.GiftType.PROPERTY);
            case "FOREIGN_TRANSFER" -> new ForeignTransferIncome(amount);
            case "MATERIAL_AID" -> new MaterialAidIncome(amount);
            case "CHILD_BENEFIT" -> throw new IllegalArgumentException(
                    "CHILD_BENEFIT должен обрабатываться как льгота, а не доход");
            default -> {
                logger.warn("Неизвестный тип записи: {}", type);
                throw new IllegalArgumentException("Неизвестный тип дохода: " + type);
            }
        };
    }

    private double parseAmount(String value) {
        try {
            double amount = Double.parseDouble(value.trim());
            if (!Double.isFinite(amount) || amount < 0) {
                throw new IllegalArgumentException("Сумма должна быть неотрицательной");
            }
            return amount;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Некорректная сумма: " + value, exception);
        }
    }
}
