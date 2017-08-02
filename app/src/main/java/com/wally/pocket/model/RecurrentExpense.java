package com.wally.pocket.model;

import com.orm.SugarRecord;
import com.wally.pocket.util.NFormatter;

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

    public String getFormattedExpenseTotal(){
        return NFormatter.maskNumber(getExpenseTotal());
    }

    public String getFormattedExpenseConcept(){
        return getExpenseConcept() == null ? "" : getExpenseConcept();
    }

    public String getFormattedApplyDay(){
        return String.valueOf(getApplyDay());
    }

}
