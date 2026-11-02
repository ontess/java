package org.example.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.factory.IncomeFactory;
import org.example.model.ChildBenefit;
import org.example.model.Income;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaxDataReader {
    private static final Logger logger = LogManager.getLogger(TaxDataReader.class);
    private final IncomeFactory incomeFactory;

    public TaxDataReader(IncomeFactory incomeFactory) {
        if (incomeFactory == null) {
            throw new IllegalArgumentException("Фабрика доходов не может быть null");
        }
        this.incomeFactory = incomeFactory;
    }

    public List<Income> readIncomes(Path path) throws IOException {
        List<Income> incomes = new ArrayList<>();
        for (String line : Files.readAllLines(path)) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }
            String[] fields = trimmed.split(";");
            if (fields[0].trim().equalsIgnoreCase("CHILD_BENEFIT")) {
                continue;
            }
            try {
                incomes.add(incomeFactory.create(fields));
            } catch (IllegalArgumentException exception) {
                logger.warn("Запись пропущена: '{}'. Причина: {}", line, exception.getMessage());
            }
        }
        logger.info("Загружено доходов: {}", incomes.size());
        return incomes;
    }

    public ChildBenefit readChildBenefit(Path path) throws IOException {
        int children = 0;
        for (String line : Files.readAllLines(path)) {
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }
            String[] fields = trimmed.split(";");
            if (fields[0].trim().equalsIgnoreCase("CHILD_BENEFIT")) {
                try {
                    if (fields.length < 2) {
                        throw new IllegalArgumentException("Нет количества детей");
                    }
                    int value = Integer.parseInt(fields[1].trim());
                    if (value < 0) {
                        throw new IllegalArgumentException("Количество детей отрицательное");
                    }
                    children = value;
                } catch (IllegalArgumentException exception) {
                    logger.warn("Некорректная льгота на детей, используется значение 0: {}", line);
                    children = 0;
                }
            }
        }
        return new ChildBenefit(children);
    }
}
