package com.example.realworld.chapter5;

import org.springframework.context.annotation.Conditional;

public class Diagnosis {

    private final ConditionalAction conditionalAction;
    private final Facts facts;
    private final boolean isPositive;

    public Diagnosis(final ConditionalAction conditionalAction, final Facts facts,  final boolean isPositive) {
        this.conditionalAction = conditionalAction;
        this.facts = facts;
        this.isPositive = isPositive;
    }

    public ConditionalAction getConditionalAction() {
        return conditionalAction;
    }

    public Facts getFacts() {
        return facts;
    }

    public boolean isPositive() {
        return isPositive;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "conditionalAction=" + conditionalAction +
                ", facts=" + facts +
                ", isPositive=" + isPositive +
                '}';
    }
}
