package org.example.model;

public class EmploymentIncome extends Income {
    public enum EmploymentType {
        MAIN, ADDITIONAL
    }

    private final EmploymentType employmentType;

    public EmploymentIncome(double amount, EmploymentType employmentType) {
        super(amount);
        if (employmentType == null) {
            throw new IllegalArgumentException("Не указан тип занятости");
        }
        this.employmentType = employmentType;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    @Override
    public String getType() {
        return employmentType == EmploymentType.MAIN
                ? "Основное место работы" : "Дополнительное место работы";
    }
}
