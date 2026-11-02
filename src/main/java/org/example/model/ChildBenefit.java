package org.example.model;

public class ChildBenefit {
    private final int childrenCount;

    public ChildBenefit(int childrenCount) {
        if (childrenCount < 0) {
            throw new IllegalArgumentException("Количество детей не может быть отрицательным");
        }
        this.childrenCount = childrenCount;
    }

    public int getChildrenCount() {
        return childrenCount;
    }
}
