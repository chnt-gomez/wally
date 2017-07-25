package com.wally.pocket.model;

import com.orm.SugarRecord;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class RecurrentExpense extends SugarRecord {

    public float getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(float expenseTotal) {
        this.expenseTotal = expenseTotal;
    }

    public String getExpenseConcept() {
        return expenseConcept;
    }

    public void setExpenseConcept(String expenseConcept) {
        this.expenseConcept = expenseConcept;
    }

    public int getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(int applyDay) {
        this.applyDay = applyDay;
    }

    public int getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(int applyStatus) {
        this.applyStatus = applyStatus;
    }

    private float expenseTotal;
    private String expenseConcept;
    private int applyDay;
    private int applyStatus;
}
