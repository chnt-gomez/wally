package com.wally.pocket.model;

import com.orm.SugarRecord;
import com.wally.pocket.util.NFormatter;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class Expense extends SugarRecord {

    private float expenseAmount;
    private long expenseApplyDate;
    private String expenseConcept;
    private Category expenseCategory;

    public int getExpenseStatus() {
        return expenseStatus;
    }

    public void setExpenseStatus(int expenseStatus) {
        this.expenseStatus = expenseStatus;
    }

    private int expenseStatus;

    public float getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(float expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public long getExpenseApplyDate() {
        return expenseApplyDate;
    }

    public void setExpenseApplyDate(long expenseApplyDate) {
        this.expenseApplyDate = expenseApplyDate;
    }

    public String getExpenseConcept() {
        return expenseConcept;
    }

    public void setExpenseConcept(String expenseConcept) {
        this.expenseConcept = expenseConcept;
    }

    public Category getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(Category expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public String getFormattedExpenseAmount(){
        return NFormatter.maskNumber(getExpenseAmount());
    }
}
